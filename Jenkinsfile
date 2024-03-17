pipeline {
    agent any
    tools {
        maven 'Maven 3.9.6'
    }
    environment {
        DockerHubCredentials=credentialsId('DockerHub_Token')
    }
    stages {
        stage('Check out') {  // Check out stage
            steps {
                git branch: 'main', url: 'https://github.com/marcuschui2022/comp367-lab2-q2'
            }
        }
        stage('Build') {  // Build maven project stage
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // sh "mvn clean install"
            }
        }
        stage('Code Coverage') {  // Code coverage stage
            steps {
                sh "mvn jacoco:report"
            }
        }
        stage('Docker Build') {  // Docker build stage
            steps {
                sh "docker build -t marcusyuk/comp367-lab3-q1:${BUILD_NUMBER} ."
            }
        }
        stage('Docker Login') {  // Docker login stage
            steps {
                withCredentials([usernamePassword(credentialsId: 'DockerHubCredentials', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh "docker login -u ${username} -p ${password}"
                }
            }
        }
        stage('Docker Push') {  // Docker push stage
            steps {
                sh "docker push marcusyuk/comp367-lab3-q1:${BUILD_NUMBER}"
            }
        }
    }
}