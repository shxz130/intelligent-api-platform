package com.gitee.itapm.dubbo.api.maven;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

import javax.annotation.Resource;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by jetty on 2018/12/9.
 */
@Mojo(name = "dubbo-api")
public class DubboApiMojo extends AbstractMojo {

    private final static String classLocation = System.getProperty("user.dir") ;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Component
    private MavenProjectHelper projectHelper;



    public void execute() throws MojoExecutionException, MojoFailureException {


        try{
            List<File> jarFileList=new ArrayList<File>();
            getAllJarFile(new File(classLocation), jarFileList);
            URL[] urls=convertFile2UrlArray(jarFileList);

            URLClassLoader classLoader=URLClassLoader.newInstance(urls,DubboApiMojo.class.getClassLoader());
        /*    for(int i=1;i<urls.length;i++){
                loadJar(classLoader,urls[i]);
            }*/
            List<String> javaFileStringList=new ArrayList<String>();
            getAllJavaFile(new File(classLocation),javaFileStringList);
            List<Class> classList=convertJavaFile2Class(javaFileStringList,classLoader);

        }catch (Exception e){
            getLog().info(e);
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


    public List<Class> convertJavaFile2Class(List<String>  javaFileStringList,URLClassLoader classLoader){
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
    }

 /*   public URL getApplicationUrl(URL[] urls){
        for(URL url:urls){
            if(url.getPath().contains("runner"))
        }
    }*/

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
    }

    public void getAllJarFile(File file,List<File> jarFileList){
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


    public static void generateApi(){

        URL[] urls;
        try {
            urls = new URL[] { new URL("file:" + classLocation) };
            URLClassLoader loader = new URLClassLoader(urls);


            String className = urls[0].getPath();
            className = className.split("(classes/)|(!/)")[1];
            className = className.replace("/", ".").replace(".class", "");
            Object obj = Class.forName(className);

            Class<?> c = loader.loadClass(className);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






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
