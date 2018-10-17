import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-10-2',
  templateUrl: './int06-10-2.component.html',
  styleUrls: ['./int06-10-2.component.css']
})
export class Int06102Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06102');
  }

}
