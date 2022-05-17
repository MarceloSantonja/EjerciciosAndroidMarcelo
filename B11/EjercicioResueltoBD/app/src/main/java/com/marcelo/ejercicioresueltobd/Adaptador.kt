package layout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.ejercicioresueltobd.Cliente
import com.marcelo.ejercicioresueltobd.Holder
import com.marcelo.ejercicioresueltobd.R

class Adaptador internal constructor(var datos: ArrayList<Cliente>) :
    RecyclerView.Adapter<Holder>()
{
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):Holder
    {
        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_layout, viewGroup, false)
        return Holder(itemView)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item: Cliente = datos[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
        return datos.size
    }

}