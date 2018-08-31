import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DropdownComponent } from './dropdown.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        DropdownComponent
    ],
    exports: [
        // Component
        DropdownComponent,
        // Modules
        CommonModule,
        FormsModule
    ]
})
export class DropdownModule { }