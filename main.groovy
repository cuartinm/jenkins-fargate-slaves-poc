pipeline {
  agent none
  stages {
    stage('Test') {
        agent {
            ecs {
                inheritFrom 'ecs-fargate-slaves'
                cpu 256
                memory 512
                image 'jenkins/inbound-agent'
            }
        }
        steps {
            sh 'echo hello'
        }
    }
  }
}