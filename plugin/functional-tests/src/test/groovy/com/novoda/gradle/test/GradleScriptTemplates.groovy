package com.novoda.gradle.test

import org.gradle.util.GradleVersion

class GradleScriptTemplates {

    static String forJavaProject() {
        return """
            buildscript {
                repositories {
                    jcenter()
                }
                dependencies {
                    classpath 'com.novoda:bintray-release:local'
                }
            }
            
            plugins { 
                id 'java-library'
            }
            
            repositories {
                jcenter()
            }
            
            dependencies {
                implementation "junit:junit:4.12"
            }
            
            apply plugin: 'com.novoda.bintray-release'
            
            publish {
                userOrg = 'novoda'
                groupId = 'com.novoda'
                artifactId = 'test'
                publishVersion = '1.0'
                desc = 'description'
            }
               """.stripIndent()
    }

    static String forAndroidProject(String androidGradlePluginVersion = '3.0.0') {
        return """
            buildscript {
                repositories {
                    google()
                    jcenter()
                }
                dependencies {
                    classpath 'com.android.tools.build:gradle:$androidGradlePluginVersion'
                    classpath 'com.novoda:bintray-release:local'
                }
            }
            
            repositories {
                google()
                jcenter()
            }
            
            apply plugin: 'com.android.library'
            apply plugin: 'com.novoda.bintray-release'
            
            android {
                compileSdkVersion 29
                ${buildTools(androidGradlePluginVersion)}

                defaultConfig {
                    minSdkVersion 16
                    versionCode 1
                    versionName '0.0.1'
                }    
                
                lintOptions {
                   tasks.lint.enabled = false
                }
            }
            
            publish {
                userOrg = 'novoda'
                groupId = 'com.novoda'
                artifactId = 'test'
                publishVersion = '1.0'
                desc = 'description'
            }
               """.stripIndent()
    }

    private static String buildTools(String androidGradlePluginVersion) {
        return GradleVersion.version(androidGradlePluginVersion) < GradleVersion.version('3.0.0') ? "buildToolsVersion '29.0.3'" : ''
    }
}
