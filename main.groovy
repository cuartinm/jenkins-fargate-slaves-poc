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
                portMappings([[containerPort: 22, hostPort: 22, protocol: 'tcp'], [containerPort: 443, hostPort: 443, protocol: 'tcp']])
            }
        }
        steps {
            sh 'echo hello'
        }
    }
  }
}