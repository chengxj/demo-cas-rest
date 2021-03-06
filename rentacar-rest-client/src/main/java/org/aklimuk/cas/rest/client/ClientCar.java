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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a client view of a car. For simplicity we duplicate the server class <code>Car</code>
 * instead of sharing it.
 * 
 * @author Alexander Klimuk
 */
@XmlRootElement
public class ClientCar {

    public Integer id;

    public String brand;

    public String model;

    public String plate;

    public ClientCar() {
        // Jackson cannot instantiate an object without default constructor
    }

    public ClientCar(Integer id, String brand, String model, String plate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
    }

}
