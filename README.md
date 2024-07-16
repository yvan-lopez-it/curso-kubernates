Comandos kubernetes

- Crear un deployment con pod para la base de datos mysql:
kubectl create deployment mysql-db --image=mysql:latest --port=3306 --dry-run=client -o yaml > deployment-mysql.yaml
- Levantar un deployment desde un archivo: kubectl apply -f .\deployment-mysql.yaml
- Ver logs: kubectl logs mysql-db-574d487746-ln4nl 
- kubectl describe pod
- kubectl get pods
- kubectl get all
- kubectl get svc
-  kubectl get services
- kubectl expose deployment mysql-db --port=3306 --type=ClusterIP
