import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-12-1',
  templateUrl: './int06-12-1.component.html',
  styleUrls: ['./int06-12-1.component.css']
})
export class Int06121Component implements OnInit {

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06121');
  }

}
