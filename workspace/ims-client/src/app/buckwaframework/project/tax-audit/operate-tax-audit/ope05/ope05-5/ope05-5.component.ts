import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope05-5',
  templateUrl: './ope05-5.component.html',
  styleUrls: ['./ope05-5.component.css']
})
export class Ope055Component implements OnInit {

  public showData: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-05500');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
