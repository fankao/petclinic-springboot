pipeline{
	agent any
	tools {
		maven "MAVEN"
	}
// 	environment {
// 		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
// 	}
    environment{
        APP_NAME=petclinic
        APP_HOST=localhost
        DB_USERNAME=postgres
        DB_PASSWORD=1234
        PORT=8088
        DB_PORT=5432
    }

 	stages {
		stage('Initialize'){
		    steps{
			echo "PATH = ${MAVEN_HOME}/bin:${PATH}"
			echo "MAVEN_HOME = /opt/maven"
		    }
		}
	     stage('Build'){
			steps{
				sh 'mvn clean install -DskipTests'
			}   
	    }
	     stage('Test'){
			 steps{
				 sh 'mvn test'
			 }
	    }
		stage('Build Docker image') {
			steps {
				sh 'docker build -t 02039921/spring-petclinic:latest .'
			}
		}
		stage('Deploy local'){
		    steps{
		        sh 'docker run '
		    }
		}
// 		stage('Push image to DockerHub') {
// 			steps {
// 				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
// 				sh 'docker push 02039921/spring-petclinic-devops:latest'
// 			}
// 		}
	}

// 	post {
// 		always {
// 			sh 'docker logout'
// 		}
// 	}

}
