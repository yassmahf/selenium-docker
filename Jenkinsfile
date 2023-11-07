pipeline{

agent none


stages{
    stage('Build Jar'){
      agent{
        docker{
            image 'maven:3.9.3-eclipse-temurin-17-focal'
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
            app = docker.build('yassmahf98/selenium')
           }

        }

    }

    stage('Push Image'){
       

       steps{
      

       script{
        docker.withRegistry('','dockerhub-creds'){
            app.push("latest")
        }
       }

       
       
       }
       
    }



}







}