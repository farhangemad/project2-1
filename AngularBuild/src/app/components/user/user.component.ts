import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service'; 
import { User } from 'src/app/models/userClass'; 
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router'; 
import users from 'src/data/users.json'; 


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'], 
  providers: [UserService]
})
export class UserComponent implements OnInit 
{
  userData: any = sessionStorage.getItem("userData"); 
  parsedUserData: any = JSON.parse(this.userData); 
  testHistory: any; 
  usersContainer: any; 
  userName: string = ''; 
  user: User = new User(null); 
  public userList:{id:number, firstName:string, lastName: string}[] = users; 
  httpOptions = 
  {
    headers: new HttpHeaders
    ({
      'Content-Type': 'text/plain'
    })
  }

  insert_user_url = 'http://localhost:8000/user/create-or-update'; 
  get_login_creds_url =  'http://localhost:8000/user/login-attempt'; 

  constructor(private userService: UserService, private http:HttpClient, private router: Router) { }

  ngOnInit(): void 
  {
    this.usersContainer = this.userService.getAll(); 
    this.userName = this.usersContainer[0].id; 
    this.loadUsers(this.userName); 
  }

  loadUsers(userName: string)
  {
    this.userService.get(userName).subscribe
    (
      res =>
        {
          this.user = new User(res);
          console.log(this.user);  
        }
    )
  }

  takeTest()
  {
    this.router.navigate(['/quiz']); 
  }

  getTestHistory()
  {
    let url = `http://localhost:8000/test/get_history/user/${this.parsedUserData.id}`; 
    this.http.get(url, this.httpOptions).subscribe
    (
      (response) =>
      {
        sessionStorage.setItem("test-history", JSON.stringify(response));
        this.testHistory = sessionStorage.getItem("test-history");  
        this.router.navigate(['/test-history']); 
      }
    )
    
  }

}
