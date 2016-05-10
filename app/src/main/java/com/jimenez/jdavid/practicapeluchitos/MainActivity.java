package com.jimenez.jdavid.practicapeluchitos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText eId, eNombre, eCantidad, eValor;
    Button bAgregar, bBuscar, bEliminar, bActualizar, bInventario, bVender, bGanancias;
    TextView tId, tNombre, tCantidad, tValor, tInventario, tGanancias;
    String aux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eId=(EditText)findViewById(R.id.eId);
        eNombre=(EditText)findViewById(R.id.eNombre);
        eCantidad=(EditText)findViewById(R.id.eCantidad);
        eValor=(EditText)findViewById(R.id.eValor);

        bAgregar =(Button)findViewById(R.id.bAgregar);
        bBuscar=(Button)findViewById(R.id.bBuscar);
        bEliminar=(Button)findViewById(R.id.bEliminar);
        bActualizar=(Button)findViewById(R.id.bActualizar);
        bInventario=(Button)findViewById(R.id.bInventario);
        bVender=(Button)findViewById(R.id.bVender);
        bGanancias=(Button)findViewById(R.id.bGanancias);

        tId=(TextView)findViewById(R.id.tId);
        tNombre=(TextView)findViewById(R.id.tNombre);
        tCantidad=(TextView)findViewById(R.id.tCantidad);
        tValor=(TextView)findViewById(R.id.tValor);
        tInventario=(TextView)findViewById(R.id.tInventario);
        tGanancias=(TextView)findViewById(R.id.tGanancias);

        PeluchitosSQLiteHelper peluchito = new PeluchitosSQLiteHelper(this);
        db=peluchito.getWritableDatabase();

        //Crear el inventario inicial:
        //Primero garantizo que no esté creado antes el inventario.

        eNombre.setText("SpiderMan");

        buscar();

        if(tInventario.getText().length()==0){

            ContentValues inventarioInicial = new ContentValues();

            inventarioInicial.put("nombre", "Iron_Man");
            inventarioInicial.put("cantidad", "10");
            inventarioInicial.put("valor", "15000");

            db.insert("Peluchitos", null, inventarioInicial);

            inventarioInicial.put("nombre", "Viuda_Negra");
            inventarioInicial.put("cantidad", "10");
            inventarioInicial.put("valor", "12000");

            db.insert("Peluchitos", null, inventarioInicial);

            inventarioInicial.put("nombre", "Capitan_America");
            inventarioInicial.put("cantidad", "10");
            inventarioInicial.put("valor", "15000");

            db.insert("Peluchitos", null, inventarioInicial);

            inventarioInicial.put("nombre", "Hulk");
            inventarioInicial.put("cantidad", "10");
            inventarioInicial.put("valor", "12000");

            db.insert("Peluchitos", null, inventarioInicial);

            inventarioInicial.put("nombre", "Bruja_Escarlata");
            inventarioInicial.put("cantidad", "10");
            inventarioInicial.put("valor", "15000");

            db.insert("Peluchitos", null, inventarioInicial);

            inventarioInicial.put("nombre", "SpiderMan");
            inventarioInicial.put("cantidad", "10");
            inventarioInicial.put("valor", "10000");

            db.insert("Peluchitos", null, inventarioInicial);

        }

        ver_Inventario();

        eNombre.setText("");

        bAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aux=bAgregar.getText().toString();

                if (aux.equals("Agregar")){

                    tNombre.setVisibility(View.VISIBLE);
                    eNombre.setVisibility(View.VISIBLE);
                    tCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setVisibility(View.VISIBLE);
                    tValor.setVisibility(View.VISIBLE);
                    eValor.setVisibility(View.VISIBLE);

                    bBuscar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);

                    bAgregar.setText("Guardar");

                }

                else{
                    String nombre = eNombre.getText().toString();
                    String cantidad = eCantidad.getText().toString();
                    String valor = eValor.getText().toString();

                    ContentValues nuevoPeluche = new ContentValues();

                    nuevoPeluche.put("nombre", nombre);
                    nuevoPeluche.put("cantidad", cantidad);
                    nuevoPeluche.put("valor", valor);

                    db.insert("Peluchitos", null, nuevoPeluche);

                    tNombre.setVisibility(View.GONE);
                    eNombre.setVisibility(View.GONE);
                    tCantidad.setVisibility(View.GONE);
                    eCantidad.setVisibility(View.GONE);
                    tValor.setVisibility(View.GONE);
                    eValor.setVisibility(View.GONE);

                    bBuscar.setVisibility(View.VISIBLE);
                    bEliminar.setVisibility(View.VISIBLE);
                    bActualizar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);

                    bAgregar.setText("Agregar");

                }

                ver_Inventario();
            }
        });

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aux=bBuscar.getText().toString();

                if (aux.equals("Buscar")){
                    tNombre.setVisibility(View.VISIBLE);
                    eNombre.setVisibility(View.VISIBLE);
                    bBuscar.setText("Ir");

                    bAgregar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);

                }
                else{
                    buscar();
                    bBuscar.setText("Buscar");
                    tNombre.setVisibility(View.GONE);
                    eNombre.setVisibility(View.GONE);

                    bAgregar.setVisibility(View.VISIBLE);
                    bEliminar.setVisibility(View.VISIBLE);
                    bActualizar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);
                }
            }
        });

        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aux=bEliminar.getText().toString();

                if (aux.equals("Eliminar")){
                    tId.setVisibility(View.VISIBLE);
                    eId.setVisibility(View.VISIBLE);
                    bEliminar.setText("Borrar");

                    bAgregar.setVisibility(View.GONE);
                    bBuscar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);
                }
                else{
                    String id =eId.getText().toString();
                    db.delete("Peluchitos", "id=" + id, null);
                    bEliminar.setText("Eliminar");

                    tId.setVisibility(View.GONE);
                    eId.setVisibility(View.GONE);

                    bAgregar.setVisibility(View.VISIBLE);
                    bBuscar.setVisibility(View.VISIBLE);
                    bActualizar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);

                }
                ver_Inventario();
            }
        });

        bActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aux=bActualizar.getText().toString();

                if (aux.equals("Actualizar")){

                    tId.setVisibility(View.VISIBLE);
                    eId.setVisibility(View.VISIBLE);
                    tNombre.setVisibility(View.VISIBLE);
                    eNombre.setVisibility(View.VISIBLE);
                    tCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setVisibility(View.VISIBLE);
                    tValor.setVisibility(View.VISIBLE);
                    eValor.setVisibility(View.VISIBLE);

                    bActualizar.setText("Guardar");

                    bAgregar.setVisibility(View.GONE);
                    bBuscar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bVender.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);

                }

                else{

                    String id = eId.getText().toString();
                    String nombre = eNombre.getText().toString();
                    String cantidad = eCantidad.getText().toString();
                    String valor = eValor.getText().toString();

                    ContentValues actualizarPeluche = new ContentValues();

                    actualizarPeluche.put("nombre", nombre);
                    actualizarPeluche.put("cantidad", cantidad);
                    actualizarPeluche.put("valor", valor);

                    db.update("Peluchitos", actualizarPeluche, "id=" + id, null);

                    tId.setVisibility(View.GONE);
                    eId.setVisibility(View.GONE);
                    tNombre.setVisibility(View.GONE);
                    eNombre.setVisibility(View.GONE);
                    tCantidad.setVisibility(View.GONE);
                    eCantidad.setVisibility(View.GONE);
                    tValor.setVisibility(View.GONE);
                    eValor.setVisibility(View.GONE);

                    bActualizar.setText("Actualizar");

                    bAgregar.setVisibility(View.VISIBLE);
                    bBuscar.setVisibility(View.VISIBLE);
                    bEliminar.setVisibility(View.VISIBLE);
                    bInventario.setVisibility(View.VISIBLE);
                    bVender.setVisibility(View.VISIBLE);
                    bGanancias.setVisibility(View.VISIBLE);

                    ver_Inventario();
                }
            }
        });

        bInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ver_Inventario();
            }
        });

        bGanancias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ver_Ganancias();

            }
        });

        bVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aux=bVender.getText().toString();

                if (aux.equals("Vender")){

                    bVender.setText("Confirmar");

                    tNombre.setVisibility(View.VISIBLE);
                    eNombre.setVisibility(View.VISIBLE);
                    tCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setVisibility(View.VISIBLE);
                    eCantidad.setText("1");

                    bAgregar.setVisibility(View.GONE);
                    bBuscar.setVisibility(View.GONE);
                    bEliminar.setVisibility(View.GONE);
                    bActualizar.setVisibility(View.GONE);
                    bInventario.setVisibility(View.GONE);
                    bGanancias.setVisibility(View.GONE);

                    ver_Inventario();

                }

                else{

                    String nombre = eNombre.getText().toString();
                    String cantidad = eCantidad.getText().toString();

                    String[] campos = new String[]{"id","nombre","cantidad","valor"};
                    String[] argumentos=new String[]{nombre};

                    Cursor c=db.query("Peluchitos", campos, "nombre=?", argumentos,null,null,null);

                    if (c.moveToFirst()){
                        tInventario.setText("");

                        do{

                            String ids = c.getString(0);
                            String nom = c.getString(1);
                            int can = c.getInt(2);
                            can=can-Integer.parseInt(cantidad);

                            if (can<0){
                                tInventario.setText("No hay esa cantidad de peluchitos "+ nombre);
                            }

                            else{

                                if (can<6){
                                    tInventario.setText(nombre + " se está agotando, sólo quedan: "+ can);
                                }

                                ContentValues venderPeluche = new ContentValues();
                                int total1=Integer.parseInt(cantidad)*c.getInt(3);
                                venderPeluche.put("nombre", nom);
                                venderPeluche.put("cantidad", cantidad);
                                venderPeluche.put("total", String.valueOf(total1));
                                db.insert("ventasPeluchitos", null, venderPeluche);

                                ContentValues quitarPeluche = new ContentValues();
                                quitarPeluche.put("nombre", nom);
                                quitarPeluche.put("cantidad", String.valueOf(can));
                                quitarPeluche.put("valor", c.getString(3));
                                db.update("Peluchitos", quitarPeluche, "Id=" + ids, null);

                                ver_Ganancias();
                                tInventario.setVisibility(View.VISIBLE);

                            }

                        }while (c.moveToNext());

                        tNombre.setVisibility(View.GONE);
                        eNombre.setVisibility(View.GONE);
                        tCantidad.setVisibility(View.GONE);
                        eCantidad.setVisibility(View.GONE);

                        bAgregar.setVisibility(View.VISIBLE);
                        bBuscar.setVisibility(View.VISIBLE);
                        bEliminar.setVisibility(View.VISIBLE);
                        bActualizar.setVisibility(View.VISIBLE);
                        bInventario.setVisibility(View.VISIBLE);
                        bGanancias.setVisibility(View.VISIBLE);

                        bVender.setText("Vender");
                    }
                    else{
                        tInventario.setText("No existe este producto");

                        bVender.setText("Vender");

                        tNombre.setVisibility(View.GONE);
                        eNombre.setVisibility(View.GONE);
                        tCantidad.setVisibility(View.GONE);
                        eCantidad.setVisibility(View.GONE);

                        bAgregar.setVisibility(View.VISIBLE);
                        bBuscar.setVisibility(View.VISIBLE);
                        bEliminar.setVisibility(View.VISIBLE);
                        bActualizar.setVisibility(View.VISIBLE);
                        bInventario.setVisibility(View.VISIBLE);
                        bGanancias.setVisibility(View.VISIBLE);

                    }
                }
            }
        });

    }

    protected void buscar(){

        //String id = eId.getText().toString();
        String nombre = eNombre.getText().toString();
        //String cantidad = eCantidad.getText().toString();
        //String valor = eValor.getText().toString();

        String[] campos = new String[]{"id","nombre","cantidad","valor"};
        String[] argumentos=new String[]{nombre};

        Cursor c=db.query("Peluchitos", campos, "nombre=?", argumentos,null,null,null);

        if (c.moveToFirst()){
            tInventario.setText("");

            do{

                String ids = c.getString(0);
                String nom = c.getString(1);
                int can = c.getInt(2);
                int val = c.getInt(3);

                tInventario.append(" "+ids+" - "+nom+" - "+can+" - "+val+"\n");

            }while (c.moveToNext());

        }
    }

    protected void ver_Inventario(){

        Cursor c=db.rawQuery("SELECT id, nombre, cantidad, valor FROM Peluchitos", null);

        tInventario.setVisibility(View.VISIBLE);
        tGanancias.setVisibility(View.GONE);

        tInventario.setText("");

        if (c.moveToFirst()){
            tInventario.append(" - Inventario - "+"\n");
            do{
                String id=c.getString(0);
                String nombre=c.getString(1);
                String cantidad=c.getString(2);
                String valor=c.getString(3);
                tInventario.append(" "+id+" - "+nombre+" - "+cantidad+" - "+valor+"\n");
            }while (c.moveToNext());

        }

    }

    protected void ver_Ganancias(){

        Cursor c=db.rawQuery("SELECT id, nombre, cantidad, total FROM VentasPeluchitos", null);
        int tot = 0;

        tGanancias.setVisibility(View.VISIBLE);
        tInventario.setVisibility(View.GONE);

        tGanancias.setText("");

        if (c.moveToFirst()){
            tGanancias.append(" - Ganancias - "+"\n");
            do{
                String id=c.getString(0);
                String nombre=c.getString(1);
                String cantidad=c.getString(2);
                tot = tot + c.getInt(3);
                String total=c.getString(3);
                tGanancias.append(" "+id+" - "+nombre+" - "+cantidad+" - "+total+"\n");
            }while (c.moveToNext());
            tGanancias.append(" Total: "+tot);
            /*tGanancias.setText(tGanancias+"\n"+"Total: "+String.valueOf(tot));*/
        }

    }


}
