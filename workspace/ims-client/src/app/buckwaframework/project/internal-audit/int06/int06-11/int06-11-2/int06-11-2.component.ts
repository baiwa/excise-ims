import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-11-2',
  templateUrl: './int06-11-2.component.html',
  styleUrls: ['./int06-11-2.component.css']
})
export class Int06112Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06112');
  }

}
