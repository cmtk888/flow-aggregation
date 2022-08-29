# Flow Aggregation

### Demo server
- A demo server is running on EC2: http://ec2-54-176-103-211.us-west-1.compute.amazonaws.com:7070/
- Example GET Request: `curl "http://ec2-54-176-103-211.us-west-1.compute.amazonaws.com:7070/flows?hour=1"`
- Example POST Request: `curl -X POST "http://ec2-54-176-103-211.us-west-1.compute.amazonaws.com:7070/flows" -H 'Content-Type: application/json' -d '[{"src_app": "foo", "dest_app": "bar", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 300, "hour": 1}]'`
- POST Request of the provide example from the PDF: `curl -X POST "http://ec2-54-176-103-211.us-west-1.compute.amazonaws.com:7070/flows" -H 'Content-Type: application/json' -d '[{"src_app": "foo", "dest_app": "bar", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 300, "hour": 1},
{"src_app": "foo", "dest_app": "bar", "vpc_id": "vpc-0", "bytes_tx": 200, "bytes_rx": 600, "hour": 1},
{"src_app": "baz", "dest_app": "qux", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 500, "hour": 1},
{"src_app": "baz", "dest_app": "qux", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 500, "hour": 2},
{"src_app": "baz", "dest_app": "qux", "vpc_id": "vpc-1", "bytes_tx": 100, "bytes_rx": 500, "hour": 2}]'`

### Prerequisite
- Ensure you have Java and Maven installed
- Maven installation guide: https://maven.apache.org/install.html
- if the `mvn -v` command does not work, please try to set the path using `export PATH=/opt/apache-maven-<versionNumber>/bin:$PATH`
 
### How to build
- cd to the folder and you should see a file `pom.xml`
- run `mvn clean install compile`

### How to run
- `java -cp target/flow-aggregation-1.0-SNAPSHOT-jar-with-dependencies.jar com.colemantung.app.App`
- The server will be run at http://localhost:7070/
- if the port is being using by another program, run `sudo lsof -i :7070` and `kill -9 <PID>` to kill that program

### Example requests
- GET Request: `curl "http://localhost:7070/flows?hour=1"`
- POST Request: `curl -X POST "http://localhost:7070/flows" -H 'Content-Type: application/json' -d '[{"src_app": "foo", "dest_app": "bar", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 300, "hour": 1}]'`

- POST Request of the provide example from the PDF: `curl -X POST "http://localhost:7070/flows" -H 'Content-Type: application/json' -d '[{"src_app": "foo", "dest_app": "bar", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 300, "hour": 1},
{"src_app": "foo", "dest_app": "bar", "vpc_id": "vpc-0", "bytes_tx": 200, "bytes_rx": 600, "hour": 1},
{"src_app": "baz", "dest_app": "qux", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 500, "hour": 1},
{"src_app": "baz", "dest_app": "qux", "vpc_id": "vpc-0", "bytes_tx": 100, "bytes_rx": 500, "hour": 2},
{"src_app": "baz", "dest_app": "qux", "vpc_id": "vpc-1", "bytes_tx": 100, "bytes_rx": 500, "hour": 2}]'`

### How to install Java, Maven, Git on EC2?
- Create a EC2 instance with Amazon Linux
- Install Java using `sudo amazon-linux-extras install java-openjdk11 -y`
- Install Git using `sudo yum install git -y`
- Install Maven following https://docs.aws.amazon.com/neptune/latest/userguide/iam-auth-connect-prerq.html
