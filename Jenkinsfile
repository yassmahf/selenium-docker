pipeline{

agent none


stages{
    stage('Build Jar'){
      agent{
        docker{
            image 'maven'
            args '-u root -v /tmp/m2:/root/.m2'

        }
      }
        steps{
            sh "mvn clean package -DskipTests"


        }

    }
 
    stage('Build Image'){

        steps{
           // sh "docker build -t=yassmahf98/selenium ."
           script{
            app = docker.build('yassmahf98/selenium:latest')
           }

        }

    }

    stage('Push Image') {
            agent any // Same as above, ensure this agent can run Docker commands
            environment {
                // Injecting credentials into the environment, just like the working pipeline
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps {
                script {
                    // Login to Docker Hub securely
                    sh 'echo $DOCKER_HUB_PSW | docker login -u $DOCKER_HUB_USR --password-stdin'
                    // Push the image
                    //app.push("latest")
                    sh 'docker push yassmahf98/selenium:latest'
                    sh "docker tag yassmahf98/selenium:latest yassmahf98/selenium:${env.BUILD_NUMBER}"
                    sh "docker push yassmahf98/selenium:${env.BUILD_NUMBER} "
                    // Logout is not necessary here as the agent will be spun down, but it can be included for security
                }
            }
        }

 post {
        always {
            sh 'docker logout'
        }
    }

}







}