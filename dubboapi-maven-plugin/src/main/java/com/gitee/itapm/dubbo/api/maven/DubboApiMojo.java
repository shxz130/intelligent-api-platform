package com.gitee.itapm.dubbo.api.maven;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.apache.maven.project.artifact.MavenMetadataSource;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * Created by jetty on 2018/12/9.
 */
@Mojo(name = "dubbo-api")
public class DubboApiMojo extends AbstractMojo {

    private final static String classPath = System.getProperty("user.dir") ;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Component
    private MavenProjectHelper projectHelper;

    private URLClassLoader urlClassLoader;

    private Set<String> classFilePathSet;

    private Set<String> jarFilePathSet;


    public DubboApiMojo() {
    }

    public void execute() throws MojoExecutionException, MojoFailureException {


        try{
            if(project.getPackaging().equals("pom")){
                return;
            }
            getLog().debug(String.format("class路径全名为[%s]",classPath));
            initClassLoader();
            generateApi();
        }catch (Exception e){
            getLog().error(e);
        }

    }

    private void generateApi(){

      try {
          for(String className:classFilePathSet){
              try {
                  className = className.split("(classes/)|(!/)")[1];
                  className = className.replace("/", ".").replace(".class", "");
                  Class clazz = Class.forName(className, true, urlClassLoader);
                  getLog().info(className);
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
  }



  public void initClassLoader() {
      File dependencyClassPathFile = new File(project.getBuild().getOutputDirectory());
      getLog().debug(String.format("输出全路径为:[%s]", project.getBuild().getOutputDirectory()));
      String packageType = project.getPackaging();
      try {
          urlClassLoader = new URLClassLoader(new URL[]{dependencyClassPathFile.toURI().toURL()}, DubboApiMojo.class.getClassLoader());
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
      if ("war".equals(packageType)) {
          String libPath = project.getBuild().getDirectory() + File.separator + project.getBuild().getFinalName() +
                  File.separator + "WEB-INFO" + File.separator + "lib";
          List<File> jarFileList = new ArrayList<File>();
          getAllJarFile(new File(libPath), jarFileList);
          loadAllFileToClassLoader(jarFileList);
      }

      loadAllFileToClassLoader(getAllJavaDependencyFileSet());

      //如果是SpringBBoot 多module工程，从子module的target目录下获取lib和classes
      Set<File> files = getAllModuleTargetLibAndClassFile();
      loadAllFileToClassLoader(files);

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


    /*public List<Class> convertJavaFile2Class(List<String>  javaFileStringList,URLClassLoader classLoader){
        List<Class> classList=new ArrayList<Class>();
        for(String name: javaFileStringList){
            try{
                String preKey="src"+File.separator+"main"+File.separator+"java"+File.separator;
                String subkey=".java";
                String beanName=name.substring(name.indexOf(preKey)+preKey.length(),name.lastIndexOf(subkey)).replace(File.separator, ".");
                Class clazz=Class.forName(beanName,true,classLoader);
                classList.add(clazz);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return classList;
    }*/


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



    /*public static void generateApi(){


        try {

            String className = urls[0].getPath();
            className = className.split("(classes/)|(!/)")[1];
            className = className.replace("/", ".").replace(".class", "");
            Object obj = Class.forName(className);

            Class<?> c = loader.loadClass(className);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
*/





   /* public void compilerJavaCode() throws MojoExecutionException, MojoFailureException {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(
                null, null, null);
        List<String> javaSourcePaths=getJavaSourcePaths(project);


        String[] array=javaSourcePaths.toArray(new String[javaSourcePaths.size()]);
        List<String> javaFileList=new ArrayList<String>();
        for(String javaDirectoryString : array){
            try{
               getAllJavaFile(new File(javaDirectoryString), javaFileList);
                File file=new File(classLocation);
                file.mkdir();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        Iterable<? extends JavaFileObject> sourcefiles = fileManager.getJavaFileObjects(javaFileList.toArray(new String[javaFileList.size()]));
        Iterable<String> options = Arrays.asList("-d", classLocation);
        compiler.getTask(null, fileManager, null, options, null, sourcefiles)
                .call();
        try {
            fileManager.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private List<String> getJavaSourcePathWithOutCollected(MavenProject mp){
        return mp.getCompileSourceRoots();
    }


    private List<String> getJavaSourcePaths(MavenProject mavenProjectParam) {
        List<String> compileSourceRootList = new ArrayList<String>();
        compileSourceRootList.addAll(getJavaSourcePathWithOutCollected(mavenProjectParam));
        if (mavenProjectParam.getCollectedProjects() != null) {
            List<MavenProject> list = mavenProjectParam.getCollectedProjects();
            for (MavenProject mavenProject : list) {
                compileSourceRootList.addAll(getJavaSourcePathWithOutCollected(mavenProject));
                if (mavenProject.getCollectedProjects() != null) {
                    List<MavenProject> mavenProjects = mavenProject.getCollectedProjects();
                    for (MavenProject mp : mavenProjects) {
                        compileSourceRootList.addAll(getJavaSourcePathWithOutCollected(mp));
                    }
                }
            }
        }
        return compileSourceRootList;
    }


    public void getAllJavaFile(File file,List<String> fileNameList){
        if(file.isDirectory()){
            for(File fileTemp:file.listFiles()){
                getAllJavaFile(fileTemp,fileNameList);
            }
        }else{
            if(file.getAbsolutePath().endsWith(".java")){
                fileNameList.add(file.getAbsolutePath());
            }
        }
    }*/


}
