import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BasicResp } from '@/model/BasicResp';
import { Router } from '@angular/router';
import { LoginData } from '@/model/LoginData';
import { AppService } from './app.service';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {


  constructor(private router: Router, private http: HttpClient,
    private appService: AppService,
    private toastr: ToastrService) { }

  userOk: boolean = false;


  public doLogin(username: string, password: string,) {
    const headers = new HttpHeaders()
      .set("Content-Type", "application/json");
    let bodyData: LoginData = new LoginData();
    bodyData.username = username;
    bodyData.password = password;

    const body = JSON.stringify(bodyData);
    console.log("Body:" + body);
    let resp: Observable<BasicResp> = this.http.post<BasicResp>(environment.loginApiUrl,
      body,
      { headers });
    resp.subscribe(val => {
      console.log(val);

      if (val.statusOk == true) {
        localStorage.setItem("token", username);
        this.userOk = true;
        this.appService.user = true;
        console.log("Login success");
        this.router.navigate(['/']);
      }
      else {
        this.toastr.error("Password error. Use admin/admin for login");
      }
    });


  }

}
