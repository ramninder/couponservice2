FROM java:8
ADD target/CouponService2-0.0.1-SNAPSHOT.jar CouponService2-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","CouponService2-0.0.1-SNAPSHOT.jar" ]