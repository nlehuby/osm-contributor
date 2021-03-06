/**
 * Copyright (C) 2016 eBusiness Information
 * <p>
 * This file is part of OSM Contributor.
 * <p>
 * OSM Contributor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * OSM Contributor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with OSM Contributor.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.jawg.osmcontributor.ui.utils.views.holders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.jawg.osmcontributor.R;
import io.jawg.osmcontributor.ui.adapters.item.ShelterType;

public class ShelterChoiceHolder extends RecyclerView.ViewHolder {
    public View poiTagLayout;
    private SelectionListener selectionListener;

    @BindView(R.id.shelter)
    ImageView shelterImg;
    @BindView(R.id.none)
    ImageView noneImg;
    @BindView(R.id.pole)
    ImageView poleImg;

    @BindView(R.id.content_layout)
    LinearLayout content;


    public ShelterChoiceHolder(View v, SelectionListener selectionListener) {
        super(v);
        this.poiTagLayout = v;
        this.selectionListener = selectionListener;
        ButterKnife.bind(this, v);
    }

    public LinearLayout getContent() {
        return content;
    }

    public void setContent(LinearLayout content) {
        this.content = content;
    }

    public ImageView getShelterImg() {
        return shelterImg;
    }

    public ImageView getNoneImg() {
        return noneImg;
    }

    public ImageView getPoleImg() {
        return poleImg;
    }

    @OnClick(R.id.shelter)
    public void onShelterClick(View v) {
        itemSelected(ShelterType.SHELTER);
    }

    @OnClick(R.id.none)
    public void onNoneClick(View v) {
        itemSelected(ShelterType.NONE);
    }

    @OnClick(R.id.pole)
    public void onPoleClick(View v) {
        itemSelected(ShelterType.POLE);
    }

    private void itemSelected(ShelterType shelterType) {
        // Apply change
        noneImg.setImageDrawable(itemView.getContext().getResources().getDrawable(shelterType.equals(ShelterType.NONE) ? R.drawable.no_shelter_on : R.drawable.no_shelter_off));
        poleImg.setImageDrawable(itemView.getContext().getResources().getDrawable(shelterType.equals(ShelterType.POLE) ? R.drawable.pole_shelter_on : R.drawable.pole_shelter_off));
        shelterImg.setImageDrawable(itemView.getContext().getResources().getDrawable(shelterType.equals(ShelterType.SHELTER) ? R.drawable.has_shelter_on : R.drawable.has_shelter_off));

        if (selectionListener != null) {
            selectionListener.shelterSelected(shelterType);
        }
    }

    public interface SelectionListener {
        void shelterSelected(ShelterType shelterType);
    }
}