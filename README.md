# Reto Curso Java 02

## Requerimientos
- El usuario debe autenticarse.
- Un autor debe tener solo un usuario.
- Un autor puede tener máximo 03 blogs.
- Solo pueden tener blogs los autores mayores de 18 años.
- Solo se puede publicar un post por día.
- Solo se puede registrar posts en blogs en estado activo.
- Solo se pueden registrar comentarios en post en estado publicado.
- Al eliminar un autor, debe realizarse una eliminación en cadena de sus blogs, post y
comentarios.
- Un usuario solo puede tener una reacción para cada post. También puede quitarle la
reacción.
- Un usuario puede comentar n veces en cada post.

## Consideraciones
Considerar los siguientes puntos respecto a la implementación de los requerimientos solicitados para el proyecto.

- ***Para el primer requerimiento***, se ha implementado un login simulado a nivel de User. Se utiliza el endpoint de users/login y se verifica que el username y password asociados a la entidad User se encuentren en la colección en MongoDB.
- ***Para el segundo requerimiento***, se ha realizado la validación a nivel de User para el registro y actualización. Se verifica en la entidad User que el Author asociado al id consignado en el registro/actualización no exista previamente.
- ***Para el tercer requerimiento***, se ha realizado la validación a nivel de Blog para el registro y actualización. Se verifica en la entidad Blog que el Author asociado al id consignado en el registro/actualización cuente con menos de 3 Blogs.
- ***Para el cuarto requerimiento***, se ha realizado la validación a nivel de Blog para el registro y actualización. Se verifica en la entidad Blog que el Author asociado al id consignado en el registro/actualización sea mayor de 18 años.
- ***Para el quinto requerimiento***, se ha realizado la validación a nivel de Post para el registro (la fecha del Post se genera automáticamente con los valores correspondientes al momento que se invocó al método POST). Se verifica que unicamente se puede realizar una publicación por día, considerando todos los elementos de tipo Author; es decir, solo se puede publicar un Post al día a nivel general.
- ***Para el sexto requerimiento***, se ha realizado la validación a nivel de Post para el registro y actualización. Se verifica en la entidad Post que el Blog asociado al id consignado en el registro/actualización se encuentre con estado activo.
- ***Para el séptimo requerimiento***, se ha realizado la validación a nivel de Comment para el registro y actualización. Se verifica en la entidad Comment que el Post asociado al id consignado en el registro/actualización se encuentre con estado publicado.
- ***Para el octavo requerimiento***, se ha implementado la regla a nivel de Author para la eliminación. Se efectúa al momento de eliminar una entidad Author, la eliminación de los Blogs, Post, Comments y Reactions asociados.
- ***Para el noveno requerimiento***, se ha implementado la regla a nivel de Reaction para el registro y actualización. Se verifica en la entidad Reaction que el User asociado al id consignado en el registro/actualización pueda añadir solo un Reaction por cada entidad Post.
- ***Para el desimo requerimiento***, se ha implementado la regla a nivel de Comment para el registro y actualización. Se verifica en la entidad Comment que el User asociado al id consignado en el registro/actualización pueda añadir n Comments en una entidad Post.
- ***Finalmente***, se ha incluido un archivo de la colección de Postman para ejecutar las pruebas requeridas (archivo Reto 4.postman_collection.json). Tener en cuenta que las fuentes del proyecto se encuentran en la rama ***main*** del repositorio en GitHub. Adicionalemente, considerar que la data para las pruebas se esta cargando automaitcamente el ejecutar la aplicación; se han utilizado los siguientes valores para la conexión a MongoDB:
-- **server.port=8080**
-- **spring.data.mongodb.host=localhost**
-- **spring.data.mongodb.port=27017**
-- **spring.data.mongodb.database=blogappdb**

