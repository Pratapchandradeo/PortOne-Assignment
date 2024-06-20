# PortOne-Assignment



<a name="readme-top"></a> 

<!-- PROJECT SHIELDS -->

  <p align="center">
    #<strong>Stripe | Financial Infrastructure to Grow Your Revenue  !</strong>
    <br />
    <a href="(https://github.com/Pratapchandradeo)"><strong>Explore the docs »</strong></a>
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#modules">Modules</a></li>
    <li><a href="#API">API</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributors">Contributors</a></li>
    <li><a href="#references">References</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is Java Bases Spring-Boot API gateway. Here I have used Payment and Customer models from Stripe. Only for testing purposes. 
Easily Create Customer and make payment gateway to make payments. No need to import third party libraries or code. Stripe API provides you all. Here I have used secured payments from Stripe to DEMO the actual working of API.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

Tech Stack :

![My Skills](https://skillicons.dev/icons?i=java,spring,maven,github,git,stripe,vscode&theme=light)
<p align="right">(<a href="#readme-top">back to top</a>)</p>



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

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Modules
This Application Consist 2 Modules

## 1. Customer Module

- Based Upon Stripe API Docs.
- It performs Registration Of Customer
- Search a customer, Get a list of customers , etc.
  
## 2.Payment Module
- This Module Will Make A Payment Object and store it Using Stripe API.
- Confirm and Capture payments using various payment methods such as Debit or Credit card, Google Pay, Amazon Pay ,etc.
- If not satisfied you can always reverse the process that is REFUND.


<!-- API -->
## API
  
   * POST Save Payment
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

   * GET Capture

 ```sh
   https://portone-assignment-production.up.railway.app/api/v1/capture_intent/pi_3PTeIg07mhhqo5690JIwgEW6
   ```
 ```sh
 
   ```
   * POST refund

 ```sh
 https://portone-assignment-production.up.railway.app/api/v1/create_refund/pi_3PTeIg07mhhqo5690JIwgEW6
   ```
 ```sh
  * GET List Payments

 ```sh
https://portone-assignment-production.up.railway.app/api/v1/get_intents
   ```
 ```sh
   ````

<!--References -->
## references
*  Stripe API docs - [](https://stripe.com/docs/api) [https://stripe.com/docs/api/payment_intents]
* Payment Intents - [https://stripe.com/docs/payments/payment-intents](https://stripe.com/docs/payments/payment-intents)
* Stripe go-SDK - [https://github.com/stripe/stripe-go](https://github.com/stripe/stripe-go)
*  Stripe Other Language SDKs - [https://github.com/stripe](https://github.com/stripe/stripe-go)
* Setup simple backend in Golang - [https://github.com/gorilla/mux](https://github.com/gorilla/mux)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
