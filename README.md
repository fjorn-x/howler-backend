# Howler

Howler is a Twitter-inspired social media platform that allows users to post short messages or "howl", follow others, share media, and customize their profile. It offers a responsive design for web and mobile.

## Demo

[Live App]()


## Running Locally

### Prerequisites

| Dependency                               | Version Requirement |
|------------------------------------------|--------------------:|
| Java                                     |                  17 |
| Maven                                    |               4.0.0 |
| MySQL                                    |               8.0.31 |

### Database

You must create a new database with the name howlerdb

### Server
You can run the server locally using Maven:

```shell
mvn spring-boot:run
```


For the Razorpay Integration to work change these properties in application.properties

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `rzp_key_id` | `string` | **Required**. Your Razorpay API key |
| `rzp_key_secret` | `string` | **Required**. Your Razorpay API secret|
| `rzp_currency` | `string` | **Required**. Currency |

### Web UI

Go to the following Github [repository](https://github.com/fjorn-x/howler-frontend)

