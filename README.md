## STORE-MICROSERVICES
Este es mi primer proyecto sobre arquitectura de microservicios con Spring Cloud.

```
tienda-microservicios/
├── .mvn/                              # Configuración de Maven
├── .gitignore
├── pom.xml                            # 1. Parent POM (Define versiones globales: Java 21, Spring Boot)
├── docker-compose.yml                 # 2. Infraestructura (Zipkin, Postgres, etc.)
├── init.sql                           # 3. Scripts iniciales de BD
├── services/                          # 4. Directorio de todos los Microservicios
│   ├── discovery-service/             # 5. Eureka Server (Registro de Servicios)
│   │   └── pom.xml                    #   (Módulo Spring Boot independiente)
│   ├── gateway-service/               # 6. API Gateway (Punto de entrada, Enrutamiento y Seguridad JWT)
│   │   └── pom.xml
│   ├── auth-service/                  # 7. Servicio de Autenticación (Login, Generación de JWT)
│   │   └── pom.xml
│   ├── category-service/              # 8. Microservicio CRUD de Categorías (Hexagonal)
│   │   └── pom.xml
│   └── product-service/               # 9. Microservicio CRUD de Productos (Hexagonal)
│       └── pom.xml
└── README.md
```