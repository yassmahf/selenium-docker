pipeline{

agent any


stages{
    stage('Build Jar'){
        steps{
            sh "mvn clean package -DskipTests"


        }

    }
 
    stage('Build Image'){

        steps{
            sh "docker build -t=yassmahf98/selenium ."

        }

    }

    stage('Push Image'){
        environment{
            DOCKER_HUB = credentials ('docker hub credentials')
        }

       steps{
         sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
         sh "docker push yassmahf98/selenium"

       
       
       }
        post{
    always{
        sh "docker logout"
    }
}

    }



}







}