import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope04-10',
  templateUrl: './ope04-10.component.html',
  styleUrls: ['./ope04-10.component.css']
})
export class Ope0410Component implements OnInit {

  public showData: boolean = false;

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04100');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }


}
