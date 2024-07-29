# Comandos kubernetes

## Minikube
- minikube stop
- minikube start --driver=hyperv

## Kubectl
- Crear un deployment con pod para la base de datos mysql. Luego genera un yaml
  - `kubectl create deployment mysql-db --image=mysql:latest --port=3306 --dry-run=client -o yaml > deployment-mysql.yaml`
- Levantar un deployment desde un archivo: 
  - `kubectl apply -f .\deployment-mysql.yaml`
- Exponer los pods del deployment:
  - `kubectl expose deployment mysql-db --port=3306 --type=ClusterIP`
- Ver logs: 
  - `kubectl logs mysql-db-574d487746-ln4nl` 
 
## Comandos generales
- `kubectl describe pod`
- `kubectl get pods`
- `kubectl get all`
- `kubectl get svc`
- `kubectl get services`

- `kubectl expose deployment msvc-usuarios --port=8001 --type=LoadBalancer`
- kubectl describe pod msvc-usuarios-546875779c-q52xg
- `minikube service msvc-usuarios --url`
- kubectl set image deployment msvc-usuarios usuarios=yvancho/usuarios:v3
- kubectl scale deployment msvc-usuarios --replicas=3

- `kubectl apply -f .\deployment-usuarios.yaml -f .\svc-usuarios.yaml`
- `kubectl apply -f .\deployment-cursos.yaml -f .\svc-cursos.yaml`

- `kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default`
