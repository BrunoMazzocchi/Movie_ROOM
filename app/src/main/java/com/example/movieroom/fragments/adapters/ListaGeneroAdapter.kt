import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieroom.bd.entities.GeneroEntity
import com.example.movieroom.databinding.ListaGeneroBinding
import com.example.movieroom.fragments.lista.FragmentListaGeneroDirections

class ListaGeneroAdapter:
    RecyclerView.Adapter<ListaGeneroAdapter.GeneroHolder>(){
    private var listadoGenero = emptyList<GeneroEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GeneroHolder {
        val binding = ListaGeneroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeneroHolder(binding)
    }

    override fun onBindViewHolder(holder: GeneroHolder, position: Int) {
        holder.bind(
            listadoGenero[position]
        )
    }

    override fun getItemCount(): Int = listadoGenero.size

    fun setData(generos: List<GeneroEntity>){
        this.listadoGenero = generos
        notifyDataSetChanged()
    }

    inner class GeneroHolder(val binding: ListaGeneroBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(genero: GeneroEntity){
            with(binding){
                TvIdGenero.text = genero.idGenero.toString()
                TvNombreGenero.text = genero.nombre

                ClFilaGen.setOnClickListener {
                    val action = FragmentListaGeneroDirections.updateListaGenero(genero)
                    it.findNavController().navigate(action)
                }
            }
        }
    }
}