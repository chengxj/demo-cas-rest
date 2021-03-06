/*
 * Copyright 2015 Alexander Klimuk
 * aklimuk@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.aklimuk.cas.rest.client;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <p>
 * For transparency of the DEMO we prefer code duplication, not code reuse, abstract classes,
 * parameters, etc. Compare to {@link NonSecuredTest}. For the same reason we prefer hardcoded
 * values instead of parametres. Compare to {@link SecuredRestClient}.
 * </p>
 * 
 * @author Alexander Klimuk
 */
public class NonSecuredRestClient {

    public ClientCar createCar(String brand, String model, String plate) throws IOException {
        // 1. Create a new car on server
        ClientCar carToCreate = new ClientCar(null, brand, model, plate);

        Response response = ClientBuilder.newClient().target("http://localhost:8080/rentacar-rest/cars").request()
                .post(Entity.entity(carToCreate, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 201) {
            throw new RuntimeException("Unexpected status: " + response.getStatus());
        }

        String createdCarUrl = response.getHeaders().getFirst("Location").toString();

        System.out.println("URL of created car: " + createdCarUrl);

        // 2. Retrieve the created car by its URL
        response = ClientBuilder.newClient().target(createdCarUrl).request().accept(MediaType.APPLICATION_JSON).get();

        ClientCar createdCar = response.readEntity(ClientCar.class);

        return createdCar;
    }

    public Collection<ClientCar> findAllCars() throws IOException {
        GenericType<Collection<ClientCar>> carCollectionType = new GenericType<Collection<ClientCar>>() {
        };

        Collection<ClientCar> cars = ClientBuilder.newClient().target("http://localhost:8080/rentacar-rest/cars").request()
                .accept(MediaType.APPLICATION_JSON).get(carCollectionType);

        return cars;
    }

    public ClientCar findCarById(Integer id) throws IOException {
        Response response = ClientBuilder.newClient().target("http://localhost:8080/rentacar-rest/cars/" + id).request()
                .accept(MediaType.APPLICATION_JSON).get();

        ClientCar car = response.readEntity(ClientCar.class);

        return car;
    }

}
