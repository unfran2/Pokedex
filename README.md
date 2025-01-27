#Introducción:
Esta aplicación intenta imitar las "Pokédex" que manejaban los maestros pokemon en la mítica serie de animación. 
En esencia, coge una lista de 150 pokemons de una api que los contiene, los muestra y permite guardarlos en una base de datos.
![Screenshot_login1](https://github.com/user-attachments/assets/66b547cb-60b1-40ae-8ba2-780197814568)

#Características y funcionamiento:
Al abrir la aplicación, se comprueba si hay una sesión iniciada y si no es así, se redirige al usuario hacia la pantalla de Login:

Para extraer los datos de la API he utilizado Retrofit y Glide (para mostrar las imágenes).
Como base de datos se usa Firestore, al igual que Firebase Authenticator para gestionar 
