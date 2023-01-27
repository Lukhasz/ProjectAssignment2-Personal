pipeline {
    agent any

    tools {
        maven "Maven-3.8.7"
    }

    stages {
        stage('clean and get latest build') {
            steps {
                sh 'mvn clean -f backend'
                echo 'downloading GitHub project...'
                git branch: 'main', credentialsId: 'dcf05bfd-e0bb-4627-b951-7eb1e10948ea', url: 'https://github.com/lukhasz/ProjectAssignment2-Personal.git'
            }
        }

        stage('build') {
            steps {
                echo 'building...'
                sh 'mvn test-compile -f backend'
                echo 'finished building'
            }
        }

        stage ('test') {
            steps {
                echo 'starting test...'
                sh 'mvn surefire:test -f backend'
                echo 'finished test'
            }
        }
        
        stage ('package') {
            steps {
                echo 'packaging...'
                sh 'mvn war:war -f backend'
                echo 'packaged!'
            }
        }
        
        stage ('move war-file') {
            steps {
                echo 'moving war file...'
                sh 'cp backend/target/ROOT.war /artifacts'
                echo 'war file moved!'
            }
        }
    }
	
	post {
		always {
			echo 'generating test report...'
			junit 'build/*reports/**/*.xml'
			echo 'test reports generated!'
		}
	}
}