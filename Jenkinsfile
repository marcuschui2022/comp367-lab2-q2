pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'
    }

    stages {
        stage('Build') {
            steps {
                git branch: 'main', url: 'https://github.com/marcuschui2022/comp367-lab2-q2'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Run Application') {
            steps {
                sh "java -jar target/lab2-0.0.1-SNAPSHOT.jar &"
            }
        }
        stage('Test Application') {
            steps {
                sh "sleep 10"
                sh 'curl -X GET http://localhost:5001/'
            }
        }
    }
}
