package com.gitee.itapm.api.maven.annotation;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.gitee.itapm.paser.ClassParseEngine;
import com.gitee.itapm.paser.bean.Catagory;
import com.gitee.itapm.paser.bean.Document;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * Created by jetty on 2018/12/9.
 */

@Mojo(name = "generate-doc")
@Execute(goal = "generate-doc", phase = LifecyclePhase.COMPILE)
public class ItapmDocMojo extends AbstractMojo {

    private final static String classPath = System.getProperty("user.dir") ;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Component
    private MavenProjectHelper projectHelper;

    private URLClassLoader urlClassLoader;

    private Set<String> classFilePathSet;

    private Set<String> jarFilePathSet;

    @Parameter
    private String systemEnName;

    @Parameter
    private String systemChName;

    @Parameter
    private String docBaseUrl;

    @Parameter
    private String docVersion;
    public ItapmDocMojo() {
    }

    public void execute() throws MojoExecutionException, MojoFailureException {


        try{
            if(project.getPackaging().equals("pom")){
                return;
            }
            initClassLoader();
            Document document=generateDocument();
            write(document);
        }catch (Exception e){
            getLog().error(e);
        }
    }

    private void write(Document document){
        getLog().info(String.format("最终生成的doc文档格式如下：%s", JSON.toJSONString(document)));
        try{
            HttpUtil.post(docBaseUrl,JSON.toJSONString(document),3000);
        }catch (Exception e){
            getLog().error(String.format("推送文档异常，docBaseUrl=%s",docBaseUrl),e);
        }
    }


    private Document generateDocument(){
        List<Class> classList=getAllNeedParseClass();
        List<Catagory> catagoryList= ClassParseEngine.parse(classList);
        return new Document(systemEnName.toUpperCase(),systemChName,docVersion,catagoryList);
    }

      private List<Class>  getAllNeedParseClass(){
          List<Class> classList=new ArrayList<Class>();
          List<String> classNameList=new ArrayList<String>();
          String currentClassName=null;
          try {
              for(String className:classFilePathSet){

                  className = className.split("classes")[1].substring(1);
                  className = className.replace(File.separator, ".").replace(".class", "");
                  classNameList.add(className);
                  currentClassName=className;
                  Class clazz = Class.forName(className, true, urlClassLoader);
                  classList.add(clazz);
              }
          } catch (Exception e) {
              getLog().info(String.format("解析的所有class文件如下：%s",classList));
              getLog().error(String.format("加载出错的class为%s",currentClassName));
              throw new RuntimeException(e);
          }
          getLog().info(String.format("解析的所有class文件如下：",classList));
          return classList;
      }



  public void initClassLoader() {
      File dependencyClassPathFile = new File(project.getBuild().getOutputDirectory());
      getLog().debug(String.format("输出全路径为:[%s]", project.getBuild().getOutputDirectory()));
      String packageType = project.getPackaging();
      try {
          urlClassLoader = new URLClassLoader(new URL[]{dependencyClassPathFile.toURI().toURL()}, ItapmDocMojo.class.getClassLoader());
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
    /*  if ("war".equals(packageType)) {
          String libPath = project.getBuild().getDirectory() + File.separator + project.getBuild().getFinalName() +
                  File.separator + "WEB-INFO" + File.separator + "lib";
          List<File> jarFileList = new ArrayList<File>();
          getAllJarFile(new File(libPath), jarFileList);
          loadAllFileToClassLoader(jarFileList);
      }*/

      loadAllFileToClassLoader(getAllJavaDependencyFileSet());

      //如果是SpringBBoot 多module工程，从子module的target目录下获取lib和classes
      Set<File> files = getAllModuleTargetLibAndClassFile();
      loadAllFileToClassLoader(files);
      Thread.currentThread().setContextClassLoader(urlClassLoader);
  }



    public Set<File> getAllModuleTargetLibAndClassFile(){
          List<String> classesPathList=getAllModuleClassesFileList();
          classFilePathSet=new HashSet<String>(classesPathList);
          jarFilePathSet=new HashSet<String>(classesPathList);
          List<String> jarFilePathList=getAllModuleJarFileList();
          jarFilePathSet.addAll(jarFilePathList);
          Set<File> resultSet=new HashSet<File>();
          Set<String> tempSet=new HashSet<String>();
          tempSet.addAll(classesPathList);
          tempSet.addAll(jarFilePathList);
          for(String filePath:tempSet){
              resultSet.add(new File(filePath));
          }
          return resultSet;
  }

  /**
   * 获取所有.class文件
   * @return
   */
    public List<String> getAllModuleClassesFileList() {
        List<String> classesFilePathList = new ArrayList<String>();
        classesFilePathList.addAll(getProjectClassesFilePathList(project, "target" + File.separator + "classes"));
        if (project.getCollectedProjects() != null) {
            List<MavenProject> list = project.getCollectedProjects();
            for (MavenProject mavenProject : list) {
                classesFilePathList.addAll(getProjectClassesFilePathList(mavenProject, "target" + File.separator + "classes"));
                if (mavenProject.getCollectedProjects() != null) {
                    List<MavenProject> mavenProjects = mavenProject.getCollectedProjects();
                    for (MavenProject mp : mavenProjects) {
                        classesFilePathList.addAll(getProjectClassesFilePathList(mavenProject, "target" + File.separator + "classes"));
                    }
                }
            }
        }
        return classesFilePathList;
    }

    /**
     * 获取SpringBoot应用程序的jar包
     *
     * @return
     */
    public List<String> getAllModuleJarFileList(){
        String key= "target" + File.separator + "BOOT-INF"+File.separator;
        List<String> jarFilePathList=new ArrayList<String>();
        jarFilePathList.addAll(getProjectJarFilePathList(project, key));
        if (project.getCollectedProjects() != null) {
            List<MavenProject> list = project.getCollectedProjects();
            for (MavenProject mavenProject : list) {
                jarFilePathList.addAll(getProjectJarFilePathList(mavenProject, key));
                if (mavenProject.getCollectedProjects() != null) {
                    List<MavenProject> mavenProjects = mavenProject.getCollectedProjects();
                    for (MavenProject mp : mavenProjects) {
                        jarFilePathList.addAll(getProjectJarFilePathList(mavenProject, key));
                    }
                }
            }
        }
        return jarFilePathList;
    }

    public List<String> getProjectJarFilePathList(MavenProject project,String path){
        List<String> fileStringList=new ArrayList<String>();
        String filePath=project.getBasedir().getAbsoluteFile().getPath();
        String targetFilePath=filePath+File.separator+path;
        getAllEndWithTypeFile(new File(targetFilePath), ".jar", fileStringList);
        return fileStringList;
    }



    public List<String> getProjectClassesFilePathList(MavenProject project,String path){
        List<String> fileStringList=new ArrayList<String>();
        String filePath=project.getBasedir().getAbsoluteFile().getPath();
        String targetFilePath=filePath+File.separator+path;
        getAllEndWithTypeFile(new File(targetFilePath), ".class", fileStringList);
        return fileStringList;
    }




    private Set<File> getAllJavaDependencyFileSet(){
        Set<File> fileSet=new HashSet();
        Set<Artifact> artifactList=null;
        try{
            Field field=project.getClass().getDeclaredField("resolvedArtifacts");
            field.setAccessible(true);
            artifactList=(Set<Artifact>)field.get(project);
        }catch (Exception e){
            getLog().error(e);
            return fileSet;
        }

        for(Artifact artifact: artifactList){
            if(artifact.getFile()==null){
                break;
            }
            fileSet.add(artifact.getFile());
        }
        return fileSet;
    }



    private void loadAllFileToClassLoader(Collection<File> jarFileCollection){
        for(File jarFile: jarFileCollection){
            try {
                loadJar(urlClassLoader, jarFile.toURI().toURL());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }


    public void loadJar(URLClassLoader urlClassLoader,URL targetUrl){
        boolean isLoader = false;
        for (URL url : urlClassLoader.getURLs()) {
            if (url.equals(targetUrl)) {
                isLoader = true;
                break;
            }
        }
        try{
            if (!isLoader) {
                Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
                add.setAccessible(true);
                add.invoke(urlClassLoader, targetUrl);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    public void getAllEndWithTypeFile(File file,String type,List<String> fileNameList){
        if(file.isDirectory()){
            for(File fileTemp:file.listFiles()){
                getAllEndWithTypeFile(fileTemp,type, fileNameList);
            }
        }else{
            if(file.getAbsolutePath().endsWith(type)){
                fileNameList.add(file.getAbsolutePath());
            }
        }
    }

    public void getAllJarFile(File file, List<File> jarFileList){
        if(file.isDirectory()){
            for(File fileTemp:file.listFiles()){
                getAllJarFile(fileTemp, jarFileList);
            }
        }else{
            if(file.getAbsolutePath().endsWith(".jar")){
                jarFileList.add(file);
            }
        }
    }


    public URL[] convertFile2UrlArray(List<File> fileList){
        URL[] urls=new URL[fileList.size()];
        for(int i=0; i<fileList.size();i++) {
            try {
                urls[i] = fileList.get(i).toURI().toURL();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return urls;

    }
}
