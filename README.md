# Introduction
Bitcoin should be seen as some kind of remarkable innovation for modern centralized financial system in this world. Bitcoin defines and operates a new kind of decentralized network which could define and deliver value in the form of bitcoin currency. 
Compared with modern centralized financial world, Bitcoin is completely decentralized network with the features of fairness, consistence and symmetry. Modern centralized financial system mainly consists of central bank and commercial bank system, and it
is based on national or government credit which is commonly unreliable and unstable in a long period and all over the world. Actually central bank plays the most centralized role in modern financial world, and it becomes the main and deep cause of financial crisis 
and unfair financial privilege, because central bank always conducts the monetary policy based on or at least influenced by some small parts of political groups, rather than most of our ordinary people. 

On the contrast, Bitcoin creates a completely new financial infrastructure for value definition and delivery, and this kind of financial infrastructure is internally based on the mathematics principle including hash digest mechanism, ECC cryptography and distributed consensus rule. Therefor Bitcoin 
network provides some kind of fair, consistent, symmetrical and stable financial infrastructure which could be seen as the revolutionary change and impact for modern centralized financial system.

Based on the above analysis and understanding of modern economics and financial principle, and the remarkable meaning of Bitcoin innovation, I finally made the decision that I could develop Bitcoin system and implement Bitcoin protocol entirely all by myself. 
Meanwhile, Java language is the most widely used computer language, so I develop Bitcoin system mainly with Java language. As the reference implementation of Bitcoin system, Bitcoin Core organizes and stores all the block and transaction data in the 
unit of block files. But in my implementation, all the blocks, transactions and UTXO accounts are organized and stored in the relational database. Here it is actually MySql database and InnoDB storage engine. As for my own purpose, my implementation is named as
BitcoinSystem, and it is regarded as my respect to Bitcoin and Satoshi. So BitcoinSystem is designed to run as a full node and could download all the blocks and transactions data from the entire worldwide Bitcoin network. The following parts describe all 
the detailed and precise system design and technical mechanism.

# General Architecture
BitcoinSystem is relatively complex system written in Java language. As a full node running in the Bitcoin P2P network, BitcoinSystem is intended to implement all aspects of Bitcoin protocol including wallet, miner and so on. Currently, the architecture of BitcoinSystem is analysed and designed 
as the diagram below. BitcoinSystem runs as a Java GUI application which provides http service as well. And it plays the role of P2P node in the Bitcoin network and communicates with other Bitcoin nodes with binary messages defined in Bitcoin protocol precisely. The content below describes all 
the main architectural components involved and combined in BitcoinSystem application.

![](https://github.com/shaojianqing/BitcoinSystem/blob/master/doc/images/general_architecture.png)
> General Architecture Design

|Seq|Module|Description|
|:-- |:--------------- |:-------------------------------------------|
|1|Context Core |This is the IOC container core just like Spring IOC, and it provides and supports annotation based context container ability. Since BitcoinSystem is intended to be developed from scratch, I implement this context core as the IOC container core, rather than using Spring IOC.|
|2|ORM Facility |This is the object relation mapping framework for domain and database operation. Because all kinds of blockchain data in BitcoinSystem are stored and managed in relational database, that kind of ORM framework is necessary to simplify the data persistence operation. For the same reason as context core, I implement this kind of ORM Facility just like ibatis.|
|3|P2P Network |This is the P2P network implementation, it includes network connection manager, peer manager, Bitcoin message packet abstraction and so on. P2P network is mainly to maintain peer status, reconnect other more peers in case of network disconnection and supports low-level message communication for Bitcoin protocol messages sync. Bitcoin works as P2P network mainly based on this component. |
|4|Messages |This is the Bitcoin message protocol implementation. It includes all the P2P messages delivered and broadcast in Bitcoin network, and defines the message data structure, implements all message processor logic. There are currently 23 kinds of Bitcoin message which contain peer status management messages, block and transaction sync messages, memory pool messages and so on. |
|5|Script Runtime |This is the script runtime mainly for transaction verification with pub key script in transaction output record and signature script in transaction input record. For flexibility consideration, it is necessary to design some kind of script mechanism so that a variety of verification could be supported easily. Bitcoin script runtime is stack-based byte code execution 
environment but without tour-complete feature supported. This is mainly because of network security and DDOS attack consideration. It mainly consists of byte code instruction table, instruction definition, operand stack and other assistance tools. Bitcoin script runtime is commonly initialized with public key script bytes and signature script bytes, and executed on the same operand stack. 
Finally, the execution result is pushed into the operand stack or throw script exception, thus verification result could be found from execution result or script exception.|
|6|Services |This is the business and data services for block and transaction process logic. They might cover block header/body persistence, block data query, transaction persistence, transaction verification status management, transaction data query and so on. As for the support for wallet, they include transaction sending logic, transaction signature generation, transaction status query and so on. 
Currently, we have block and transaction service for persistence and update operation, block and transaction query service for query operation, and transaction send service for wallet usage. |
|7|Wallet Core |This is the wallet business core for Bitcoin wallet feature or function implementation. It provides wallet core abilities including transaction validation, signature generation, transaction sending, balance calculation and so on. Both API server and GUI interface relies on wallet core to support transaction operation by end users.|
|8|Miner |This is the mining logic implementation for POW consensus rule. Since Bitcoin takes POW as its unique Bitcoin cash issuing method, miner module is much necessary for a full node to cooperate with others in Bitcoin network. As we all know, the real hash rate has grown to a much large scale with huge optimized GPU farm. So it is not practical and efficient enough for BitcoinSystem to mine real Bitcoin currency.
But as full implementation of Bitcoin protocol, miner is still a necessary component to be implemented. |
|9|Utility Tools |This is some utilities tools supporting various usage, like byte operation, hash calculation, address conversion, hex operation and so on. Many components call this part to implement some particular feature or function, so it provides very common usage.|
|10|Crypto |This is the fundamental module for BitcoinSystem. Since Bitcoin is based on hash and crypto mechanism to construct its security, consistence and symmetry, crypto module provides signature generation and verification abilities based on ECC algorithm. It includes ECC key and signature feature implementation and signature context structure for transaction signature operation and verification. |
|11|Entities |This is the entity layer for database access and operation. BitcoinSystem chooses relational database as its persistence storage facility rather than the form of block file in Bitcoin Core. So some kind of entity layer is much necessary here. Entity layer contains all the domain data objects mapping database table and the corresponding data access objects. It also contains the related configuration xml file. 
The detailed design is shown in the database design section below.|
|12|API Server |This is the API server component in BitcoinSystem. Just as provided in Bitcoin Core, API server provides almost the same block and transaction api interface in the form of RESTFul JSON RPC pattern. This component could be seen as the api layer based on wallet core, including block and transaction query interface, transaction signature and sending interface.|
|13|GUI Interface |This is the GUI Interface presented for end user. It is developed based on Java Swing GUI framework and deeply customized for my own feeling. GUI interface also relies on blockchain and wallet core components and provides block list and query function, transaction search function, transaction signature and sending function. In addition, because BitcoinSystem needs to maintain peer status, 
GUI interface also provides peer status management function. |

# Database Entity Design

![](https://github.com/shaojianqing/BitcoinSystem/blob/master/doc/images/database_design.png)
> Database Structure Design

|Seq|Module|Description|
|:-- |:--------------- |:-------------------------------------------|
|1|block | |
|2|transaction | |
|3|transaction_block | |
|4|transaction_spend | |
|5|transaction_input | |
|6|transaction_output | |
|7|transaction_address | |

# Block and Transaction Structure
![](https://github.com/shaojianqing/BitcoinSystem/blob/master/doc/images/blockchain_structure.png)
> Blockchain Data Structure

### Main Data Structure in Bitcoin Blockchain 

### UTXO Transaction Model Description

### Bitcoin Asset Ownership and Address
 