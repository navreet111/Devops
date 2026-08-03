pipeline {
    agent any

    stages {

        stage('Build Auth Service') {
            steps {
                dir('auth-service') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Quantity Service') {
            steps {
                dir('quantity-service') {
                    sh 'mvn clean package'
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

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-token',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Docker Build') {
            steps {
                dir('auth-service') {
                    sh 'docker build -t navreet1511/auth-service:latest .'
                }

                dir('quantity-service') {
                    sh 'docker build -t navreet1511/quantity-service:latest .'
                }

                dir('quantity-measurement-frontend') {
                    sh 'docker build -t navreet1511/quantity-measurement-frontend:latest .'
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push navreet1511/auth-service:latest'
                sh 'docker push navreet1511/quantity-service:latest'
                sh 'docker push navreet1511/quantity-measurement-frontend:latest'
            }
        }

        stage('Deploy Containers') {
            steps {
                sh '''
                docker stop auth-service || true
                docker rm auth-service || true
                docker run -d --name auth-service -p 8081:8080 navreet1511/auth-service:latest

                docker stop quantity-service || true
                docker rm quantity-service || true
                docker run -d --name quantity-service -p 8082:8080 navreet1511/quantity-service:latest

                docker stop quantity-frontend || true
                docker rm quantity-frontend || true
                docker run -d --name quantity-frontend -p 80:80 navreet1511/quantity-measurement-frontend:latest
                '''
            }
        }
    }
}