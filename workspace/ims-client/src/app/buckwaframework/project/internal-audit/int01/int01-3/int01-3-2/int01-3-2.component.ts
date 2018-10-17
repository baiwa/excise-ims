import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";

declare var $: any;
@Component({
  selector: "app-int01-3-2",
  templateUrl: "./int01-3-2.component.html",
  styleUrls: ["./int01-3-2.component.css"]
})
export class Int0132Component implements OnInit {
  private productList: any[];

  private selectedProduct: string = "สุรา";

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01320');
  
  }
  onChange(newValue) {
    this.selectedProduct = newValue; // don't forget to update the model here
  }


}
