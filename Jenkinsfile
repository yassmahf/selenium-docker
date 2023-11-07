pipeline{

agent none


stages{
    stage('Build Jar'){
      agent{
        docker{
            image 'maven:3.9.3-eclipse-temurin-17-focal'
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
            app = docker.build('yassmahf98/selenium')
           }

        }

    }

    stage('Push Image'){
        environment{
            DOCKER_HUB = credentials ('docker hub credentials')
        }

       steps{
       //  sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
       //  sh "docker push yassmahf98/selenium"

       script{
        docker.withRegistry('','docker hub credentials'){
            app.push("latest")
        }
       }

       
       
       }
       
    }



}







}