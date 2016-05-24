namespace java com.buildhappy
struct User{
 1:required i32 userId
 2:required string username
 3:required string password
}
service ThriftService{
 void addUser(1:User user)
 User queryUser(1:i32 userId)
 list<User> queryUserList()
 map<string,string> queryUserNamePass()
 map<i32,User> queryUserMap() 
}
