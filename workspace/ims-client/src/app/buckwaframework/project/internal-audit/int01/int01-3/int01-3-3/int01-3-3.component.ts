import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int01-3-3',
  templateUrl: './int01-3-3.component.html',
  styleUrls: ['./int01-3-3.component.css']
})
export class Int0133Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01330');
  }

}
