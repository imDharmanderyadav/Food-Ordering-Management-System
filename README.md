# Food Ordering Management System

The Food Ordering Management System is a Java-based project designed to streamline the operations of a cafe offering pickup and delivery services. This system enables customers to browse the menu, place orders, and choose between pickup and delivery options. 

## Features
- **User Registration and Login:** Allows customers to create an account and log in to access their personalized experience.
- **Menu Browsing:** Customers can view the cafe's menu, including various food items with details like prices and descriptions.
- **Order Placement:** Users can select items, add them to their cart, and place orders for pickup or delivery.
- **Order History:** Customers can view their past orders and track the status of current orders.
- **Admin Interface:** Admins can manage menu items, view orders, and maintain overall system operations.

## Technology Stack
- **Programming Language:** Java
- **Backend Framework:** Hibernate
- **Database:** MySQL
- **IDE:** Eclipse / IntelliJ IDEA
- **Tools:** Maven, JDK

## Database Design
The system's database consists of the following key tables:
- **Admin:** Stores admin details (id, email, password, username).
- **Users:** Contains user information (userId, email, password, username, userPhone).
- **Menus:** Holds menu details (id, menuName).
- **Menu_Items:** Stores individual food item details (id, itemName, price, menu_id).
- **Cart:** Manages the cart items (id, totalAmount, user_id).
- **Orders:** Keeps track of order information (orderId, orderDate, status, total_amount, user_id).
- **Payment:** Records payment details (paymentId, amount, paymentDate, paymentMethod).

## Future Scope
- **Web/Mobile Application Development:** Expand the system to include web and mobile platforms for broader access.
- **Integration with Payment Gateways:** Facilitate online payments through various payment gateways.
- **Recommendation System:** Implement a recommendation engine to provide personalized suggestions based on user preferences.

## Contribution
This project is developed by:
- **Bhumika Sablok**
- **Dharmander Yadav**

## Getting Started
To get started with the Food Ordering Management System, clone the repository and set up the project in your favorite IDE. Ensure you have MySQL installed and configure the database according to the design mentioned above.

```bash
git clone https://github.com/imDharmanderyadav/Food-Ordering-Management-System.git
