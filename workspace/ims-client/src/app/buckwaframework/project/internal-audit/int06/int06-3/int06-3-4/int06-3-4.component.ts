import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-3-4',
  templateUrl: './int06-3-4.component.html',
  styleUrls: ['./int06-3-4.component.css']
})
export class Int0634Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06340');
  }

}
