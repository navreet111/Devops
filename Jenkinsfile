pipeline {
    agent any

    stages {

        stage('Build Auth Service') {
            steps {
                dir('auth-service') {
                    sh './mvnw clean package'
                }
            }
        }

        stage('Build Quantity Service') {
            steps {
                dir('quantity-service') {
                    sh './mvnw clean package'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('quantity-measurement-frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        // Docker build, Docker login, Docker push, Deploy...
    }
}