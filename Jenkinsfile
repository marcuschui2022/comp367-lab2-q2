pipeline {
    agent any
    tools {
        maven 'Maven 3.9.6'
    }
    environment {
        DOCKERHUB_PWD=credentials('DockerHub_Token')
    }
    stages {
        stage('Check out') {  // Check out stage
            steps {
                git branch: 'main', url: 'https://github.com/marcuschui2022/comp367-lab2-q2'
            }
        }
        stage('Build') {  // Build maven project stage
            steps {
                sh "mvn clean package"
//                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // sh "mvn clean install"
            }
        }
        stage('Pre Code Coverage') {  // Code coverage stage
                    steps {
                        sh "mvn clean verify"
                    }
        }
        stage('Code Coverage') {  // Code coverage stage
            steps {
                sh "mvn test jacoco:report"
            }
        }
//         stage('Build & Test with JaCoCo') {
//             steps {
//                 script {
//                     sh 'mvn clean verify jacoco:report'
//                 }
//             }
//          }
        stage('Docker Build') {  // Docker build stage
            steps {
                sh "docker build -t marcusyuk/comp367-lab3-q1:${BUILD_NUMBER} ."
            }
        }
        stage('Docker Login') {  // Docker login stage
            steps {
               sh "docker login -u marcusyuk -p ${DOCKERHUB_PWD}"
            }
        }
        stage('Docker Push') {  // Docker push stage
            steps {
                sh "docker push marcusyuk/comp367-lab3-q1:${BUILD_NUMBER}"
            }
        }
    }

    post {
        success {
            jacoco(
                execPattern: '**/build/jacoco/*.exec',
                classPattern: '**/build/classes/java/main',
                sourcePattern: '**/src/main'
            )
        }
    }
}