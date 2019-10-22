#include<stdio.h>
#include <windows.h>


main(){
    int 	k = system("wget --post-data=\"callback=dr155628154032&DDDDD=****UserName(edit"***"your username)***%40telecom&upass=****Password(edit"***"your password)***&0MKKey=123456&R1=0&R3=0&R6=0&para=00&v6ip=&_=1556281524900\" \"http://172.16.254.6/drcom/login \" --no-check-certificate --delete-after -t 1 -T 3");
	printf("%d \n",k);
}