import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int01-3-4',
  templateUrl: './int01-3-4.component.html',
  styleUrls: ['./int01-3-4.component.css']
})
export class Int0134Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01340');
  }

}
