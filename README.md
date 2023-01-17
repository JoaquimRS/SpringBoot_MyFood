<p align="center"><a href="https://laravel.com" target="_blank"><img src="https://imgs.search.brave.com/DphGQWcBBoYtG0TN_eZLT0LdQtuwiHbMeW6kH_2muh4/rs:fit:600:315:1/g:ce/aHR0cHM6Ly9uaWl4/ZXIuY29tL3dwLWNv/bnRlbnQvdXBsb2Fk/cy8yMDIwLzExL3Nw/cmluZy1ib290LnBu/Zw" width="400" alt="Laravel Logo"></a></p>

[spring]: https://spring.io/projects/spring-boot
[hibernate]: https://hibernate.org/orm/
[myfood]: http://github.com/gfmois/Vue_Laravel_Go_Spring_MyFood.git

## ABOUT THIS PROJECT

> This server is intended for use by customers, who can make reservations for lunch, dinner or take out at the restaurant, create an account to order online, or modify their profile and view their order statistics for the year or the reservations made.

## BUILT WITH

- [Spring] - Framework
- [Hibernate] - ORM

## API

URL
http://localhost:8080/api/

The available endpoints are:

<table>
  <tr>
    <th>Function</th>
    <th>Request</th>
    <th>Route</th>
    <th>Params</th>
    <th>Body</th>
    <th>Response</th>
  </tr>
  <tr>
    <th colspan="6" class="title" class="title">Clients</th>
  </tr>
  <tr>
    <td>getProfile()</td>
    <td>GET</td>
    <td>/client/profile</td>
    <td>{ header: Token }</td>
    <td></td>
    <td>[ User Information + Token ]</td>
  </tr>
  <tr>
    <td>updateProfile()</td>
    <td>PUT</td>
    <td>/client/profile</td>
    <td>{ Heder: Token }</td>
    <td>New User Information</td>
    <td>[ User Information + Token ]</td>
  </tr>
  <tr>
    <th colspan="6" class="title">Categories</th>
  </tr>
  <tr>
    <td>getCategories()</td>
    <td>GET</td>
    <td>/categorias</td>
    <td></td>
    <td></td>
    <td>[ Categories ]</td>
  </tr>
  <tr>
    <th colspan="6" class="title">Allergens</th> 
  </tr>
  <tr>
    <td>getAllergens()</td>
    <td>GET</td>
    <td>/productos/alergenos</td>
    <td></td>
    <td></td>
    <td>[ Allergens ]</td>
  </tr>
  <tr>
    <td>getAllergen()</td>
    <td>GET</td>
    <td>/productos/alergenos/:id-alergeno</td>
    <td></td>
    <td></td>
    <td>[ Allergen ]</td>
  </tr>
  <tr>
    <th colspan="6" class="title">Productos</th>
  </tr>
  <tr>
    <td>getProducts()</td>
    <td>GET</td>
    <td>/productos</td>
    <td></td>
    <td></td>
    <td>[ Products ]</td>
  </tr>
  <tr>
    <td>getFilteredProducts()</td>
    <td>GET</td>
    <td>/productos/filtro</td>
    <td>[ Categories, Order, Range, Pagination ]</td>
    <td></td>
    <td>[ Products ]</td>
  </tr>
  <tr>
    <td>searchProducts()</td>
    <td>GET</td>
    <td>/productos/search/:producto</td>
    <td></td>
    <td></td>
    <td>[ Products ]</td>
  </tr>
  <tr>
    <td>getProductDetails()</td>
    <td>GET</td>
    <td>/productos/:slug_producto</td>
    <td></td>
    <td></td>
    <td>[ Producto ]</td>
  </tr>
  <tr>
    <th colspan="6" class="title">Auth</th>
  </tr>
  <tr>
    <td>register()</td>
    <td>POST</td>
    <td>/auth/register</td>
    <td></td>
    <td>{ User Information }</td>
    <td>[ User Information + Token ]</td>
  </tr>
  <tr>
    <td>login()</td>
    <td>POST</td>
    <td>/auth/login</td>
    <td></td>
    <td>{ User Information }</td>
    <td>[ User Information + Token ]</td>
  </tr>
  <tr>
    <th colspan="6" class="title">Orders</th>
  </tr>
  <tr>
    <td>getOrders()</td>
    <td>GET</td>
    <td>/pedidos</td>
    <td> { Header: Token } </td>
    <td></td>
    <td>[ User Orders ]</td>
  </tr>
  <tr>
    <td>addOrder()</td>
    <td>POST</td>
    <td>/pedidos</td>
    <td>{ Header: Token }</td>
    <td>{ Order Information }</td>
    <td>[ { msg: "Pedido Creado" } ]</td>
  </tr>
  <tr>
    <th colspan="6" class="title">Reserves</th>
  </tr>
  <tr>
    <td>getReserves()</td>
    <td>GET</td>
    <td>/reservas</td>
    <td> { Header: Token } </td>
    <td></td>
    <td>[ User Reserves ]</td>
  </tr>
  <tr>
    <td>createReserve()</td>
    <td>POST</td>
    <td>/reservas</td>
    <td>{ Header: Token }</td>
    <td>{ Reserve Information }</td>
    <td>[ { msg: "Reserva Realizada" } ]</td>
  </tr>
  <tr>
    <td>getReserve()</td>
    <td>GET</td>
    <td>/reservas/:id_reserva</td>
    <td> { Header: Token }</td>
    <td></td>
    <td>[ Reserve ]</td>
  </tr>
  <tr>
    <td>getImage()</td>
    <td>GET</td>
    <td>/reservas/image</td>
    <td></td>
    <td></td>
    <td>[ Image ]</td>
  </tr>
  <tr>
    <td>getPDFReserve()</td>
    <td>GET</td>
    <td>/reservas/pdf/_id_reserva</td>
    <td>{ Header: Token }</td>
    <td></td>
    <td>[ Reserve ]</td>
  </tr>
  <tr>
    <td>getBannedDays()</td>
    <td>GET</td>
    <td>/getBannedDays</td>
    <td>{ n_comensales: Number, servicio: String }</td>
    <td></td>
    <td>[ Banned Days to Reserve ]</td>
  </tr>
  
</table>

## FEATURES

- JWT Auth
- Guards On Routes
- Served Dockerized
- Working as a submodule of [MyFood]

## USAGE

This application is created to be used in another project ([MyFood]) but if you want to launch it you should keep in mind that you have to create an `application.properties` and within this add the configuration towards the database and a `secret` for the JWT.

Your `application.properties` should be like this:

```properties
# application.properties
server.servlet.context-path=/api
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${db_server}:${db_port}/${db}
spring.datasource.username=${db_user}
spring.datasource.password=${db_password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect

springboot_myfood.auth.token.sign-key=${JWT Secret}
springboot_myfood.auth.token.valid-time=3000000
```

To be launched simply run the `mvn spring-boot:run` in your terminal inside the folder created after cloning the repository.

## AUTHORS

<div class="authors">
    <div>
  <a href="https://github.com/gfmois"><img src="https://avatars.githubusercontent.com/u/102977172?s=400&v=4" alt="Creator gfmois" width="150" height="150"></a>
  <h3>gfmois</h3>
</div>
<div>
  <a href="https://github.com/JoaquimRS"><img src="https://avatars.githubusercontent.com/u/94555035?v=4" alt="Creator JoaquimRS" width="150" height="150"></a>
  <h3>JoaquimRS</h3>
</div>
</div>

<style>
    .title {
        background: #f2f2f2;
    }

    .authors {
        display: flex;
        align-items: center;
        margin: 10px;
        flex-direction: row;
        justify-content: center;
        gap: 50px
    }

    .authors > div {
        text-align: center;
    }

    img {
        border-radius: 50%;
        margin-bottom: 10px;
    }

</style>
