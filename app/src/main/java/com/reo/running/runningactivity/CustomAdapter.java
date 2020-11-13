package com.reo.running.runningactivity;


//// customListはrecyclerViewのコンテンツとしてに表示するString配列のデータ
class CustomAdapter(private val customList: Array<String>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

// ViewHolderクラス(別ファイルに書いてもOK)
class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val sampleImg = view.sampleImg
        val sampleTxt = view.sampleTxt
        }

        // getItemCount onCreateViewHolder onBindViewHolderを実装
        // 上記のViewHolderクラスを使ってViewHolderを作成
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(item)
        }

        // recyclerViewのコンテンツのサイズ
        override fun getItemCount(): Int {
        return customList.size
        }

        // ViewHolderに表示する画像とテキストを挿入
        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.sampleImg.setImageResource(R.mipmap.ic_launcher_round)
        holder.view.sampleTxt.text = customList[position]
        }
        }
