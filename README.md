#Introducción:
Esta aplicación intenta imitar las "Pokédex" que manejaban los maestros pokemon en la mítica serie de animación. 
En esencia, coge una lista de 150 pokemons de una api que los contiene, los muestra y permite guardarlos en una base de datos.

#Características y funcionamiento:
Al abrir la aplicación, se comprueba si hay una sesión iniciada y si no es así, se redirige al usuario hacia la pantalla de Login:
![Screenshot_login1](https://github.com/user-attachments/assets/66b547cb-60b1-40ae-8ba2-780197814568)

Si el correo introducido no está registrado, lleva hacia una pantalla de registro:
![imagen](https://github.com/user-attachments/assets/88ffdfd1-716c-4eab-8b18-57ed1b8df13a)

una vez que el usuario está registrado y ha hecho su login, se dirige directamente hacia la pestaña "Mis Pokemons", donde se muestran aquellos pokemons que el usuario ha capturado:
![imagen](https://github.com/user-attachments/assets/a9d8c9de-01bd-4f67-8cb8-54aa5b4771f7)

Al pulsar en la pestaña central (Pokédex), se muestra un recyclerview con todos los pokemons que la consulta está obteniendo de la Api:
![imagen](https://github.com/user-attachments/assets/b427b0a0-8c1a-48d6-bf2b-e87b0cc372f4)
Los cardview del recycler son clickables y al pulsarlos ejecutan un método que guarda el pokemon seleccionado como un documento de una colección de una base de datos Cloud Firestore. De allí los lee y los añade al recycler de la pantalla "Mis Pokemons".
Como última pestaña está la pestaña de configuración, la cual usando SharedPreference, guarda las preferencias del usuario como el idioma (Español como predeterminado e inglés como alternativa), la opción de cerrar sesión o el apartado "Acerca de".

#Tecnologías Utilizadas:
Para extraer los datos de la API he utilizado Retrofit y Glide (para mostrar las imágenes).
Como base de datos se usa Firestore, al igual que Firebase Authenticator para gestionar el registro, login y cierre de sesión de usuarios.

#Conclusiones:
Esta es la primera versión de la aplicación y está lejos de ser lo que quiero (ni tan siquiera lo que debería ser), pero confío en seguir adquiriendo conocimientos para lograr que las próximas aplicaciones que desarrolle sean exactamente lo que quiero que sean.
