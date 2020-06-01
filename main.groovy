pipeline {
  agent none
  stages {
    stage('Test') {
        agent {
            ecs {
                inheritFrom 'ecs-fargate-slaves'
                cpu 256
                memory 512
                image 'jenkinsci/jnlp-slave'
            }
        }
        steps {
            sh 'echo hello'
        }
    }
  }
}