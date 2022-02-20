import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatButtonModule} from '@angular/material/button';
import {MatTabsModule} from '@angular/material/tabs';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatDividerModule} from '@angular/material/divider';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatTableModule} from '@angular/material/table';
import {MatStepperModule} from '@angular/material/stepper';
import {MatSortModule} from '@angular/material/sort';
import {MatBadgeModule} from '@angular/material/badge';
import {ReactiveFormsModule} from '@angular/forms';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatDialogModule} from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatOptionModule} from '@angular/material/core';
import {MatTooltipModule} from '@angular/material/tooltip';
import {NgModule} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';

const materials = [MatSidenavModule,
  MatToolbarModule,
  MatListModule,
  MatMenuModule,
  MatTooltipModule,
  MatSnackBarModule,
  MatButtonModule,
  MatButtonModule,
  MatMenuModule,
  MatTabsModule,
  MatRadioModule,
  MatProgressSpinnerModule,
  MatCheckboxModule,
  MatCardModule,
  MatListModule,
  MatIconModule,
  MatTooltipModule,
  MatSnackBarModule,
  MatSlideToggleModule,
  MatDividerModule,
  MatDatepickerModule,
  MatIconModule,
  MatAutocompleteModule,
  MatOptionModule,
  MatExpansionModule,
  MatProgressBarModule,
  MatRadioModule,
  MatDialogModule,
  MatPaginatorModule,
  ReactiveFormsModule,
  MatTableModule,
  MatStepperModule,
  MatSortModule,
  MatBadgeModule,
  MatFormFieldModule
];

@NgModule({
  declarations: [],
  imports: [
    ...materials
  ],
  exports: [
    ...materials
  ]
})
export class SharedModule {
  constructor() {
  }
}
