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
                logDriver 'awslogs'
                logDriverOptions([[name: 'awslogs-group', value:'/ecs/ecs-fargate-slaves'], [name: 'awslogs-region', value: 'us-east-2'], [name: 'awslogs-stream-prefix', value: 'ecs']])
                portMappings([[containerPort: 22, hostPort: 22, protocol: 'tcp'], [containerPort: 443, hostPort: 443, protocol: 'tcp']])
            }
        }
        steps {
            sh 'echo hello'
        }
    }
  }
}