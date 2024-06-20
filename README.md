# PortOne-Assignment







<!-- ABOUT THE PROJECT -->
## About The Project

This project is a Java-based Spring Boot API gateway that integrates Stripe's Payment and Customer models for demonstration purposes. It allows you to easily create customers and implement a payment gateway for processing payments, leveraging Stripe's API for secure transactions. The implementation showcases the actual workings of the API without the need to import additional third-party libraries or code.





### Built With

Tech Stack :

![My Skills](https://skillicons.dev/icons?i=java,spring,maven,github,git,vscode&theme=light)




<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
  
  * Spring Tool Suite 4

 ```sh
   https://spring.io/tools
   ```
  
   * Maven Dependencies

 ```sh
   https://mvnrepository.com/
   ```
   
   * StripeAPI 

 ```sh
  https://stripe.com/in
   ```




## 1. Customer Module

- Based on Stripe API documentation.
- Handles customer registration.
- It allows searching for a customer and retrieving a list of customers.
  
## 2.Payment Module
- creates and stores payment objects using the Stripe API.
- Confirms and captures payments using various methods such as debit or credit cards.
- Provides the ability to issue refunds if necessary.


<!-- API -->
## API
  
   * POST  Payment
 ```sh
   https://portone-assignment-production.up.railway.app/api/v1/create_intent
   ```
 ```sh
 Body :
{
    "amount":80000,
    "currency":"INR"
}

   ```

   * POST Capture

 ```sh
   https://portone-assignment-production.up.railway.app/api/v1/capture_intent/pi_3PTeIg07mhhqo5690JIwgEW6
   ```

   * POST refund

 ```sh
 https://portone-assignment-production.up.railway.app/api/v1/create_refund/pi_3PTeIg07mhhqo5690JIwgEW6
   ```
 
  * GET List Payments

 ```sh
https://portone-assignment-production.up.railway.app/api/v1/get_intents
   ```


<!--References -->
## references
*  Stripe API docs - [](https://stripe.com/docs/api) [https://stripe.com/docs/api/payment_intents]
* Payment Intents - [https://stripe.com/docs/payments/payment-intents](https://stripe.com/docs/payments/payment-intents)
* Stripe go-SDK - [https://github.com/stripe/stripe-go](https://github.com/stripe/stripe-go)
*  Stripe Other Language SDKs - [https://github.com/stripe](https://github.com/stripe/stripe-go)
* Setup simple backend in Golang - [https://github.com/gorilla/mux](https://github.com/gorilla/mux)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
