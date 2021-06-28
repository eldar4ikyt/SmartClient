package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLakes extends WorldGenerator {
   private Block field_150556_a;

   public WorldGenLakes(Block p_i45455_1_) {
      this.field_150556_a = p_i45455_1_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      for(p_180709_3_ = p_180709_3_.func_177982_a(-8, 0, -8); p_180709_3_.func_177956_o() > 5 && p_180709_1_.func_175623_d(p_180709_3_); p_180709_3_ = p_180709_3_.func_177977_b()) {
         ;
      }

      if(p_180709_3_.func_177956_o() <= 4) {
         return false;
      } else {
         p_180709_3_ = p_180709_3_.func_177979_c(4);
         boolean[] aboolean = new boolean[2048];
         int i = p_180709_2_.nextInt(4) + 4;

         for(int j = 0; j < i; ++j) {
            double d0 = p_180709_2_.nextDouble() * 6.0D + 3.0D;
            double d1 = p_180709_2_.nextDouble() * 4.0D + 2.0D;
            double d2 = p_180709_2_.nextDouble() * 6.0D + 3.0D;
            double d3 = p_180709_2_.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
            double d4 = p_180709_2_.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
            double d5 = p_180709_2_.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

            for(int l = 1; l < 15; ++l) {
               for(int i1 = 1; i1 < 15; ++i1) {
                  for(int j1 = 1; j1 < 7; ++j1) {
                     double d6 = ((double)l - d3) / (d0 / 2.0D);
                     double d7 = ((double)j1 - d4) / (d1 / 2.0D);
                     double d8 = ((double)i1 - d5) / (d2 / 2.0D);
                     double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                     if(d9 < 1.0D) {
                        aboolean[(l * 16 + i1) * 8 + j1] = true;
                     }
                  }
               }
            }
         }

         for(int k1 = 0; k1 < 16; ++k1) {
            for(int l2 = 0; l2 < 16; ++l2) {
               for(int k = 0; k < 8; ++k) {
                  boolean flag = !aboolean[(k1 * 16 + l2) * 8 + k] && (k1 < 15 && aboolean[((k1 + 1) * 16 + l2) * 8 + k] || k1 > 0 && aboolean[((k1 - 1) * 16 + l2) * 8 + k] || l2 < 15 && aboolean[(k1 * 16 + l2 + 1) * 8 + k] || l2 > 0 && aboolean[(k1 * 16 + (l2 - 1)) * 8 + k] || k < 7 && aboolean[(k1 * 16 + l2) * 8 + k + 1] || k > 0 && aboolean[(k1 * 16 + l2) * 8 + (k - 1)]);
                  if(flag) {
                     Material material = p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(k1, k, l2)).func_177230_c().func_149688_o();
                     if(k >= 4 && material.func_76224_d()) {
                        return false;
                     }

                     if(k < 4 && !material.func_76220_a() && p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(k1, k, l2)).func_177230_c() != this.field_150556_a) {
                        return false;
                     }
                  }
               }
            }
         }

         for(int l1 = 0; l1 < 16; ++l1) {
            for(int i3 = 0; i3 < 16; ++i3) {
               for(int i4 = 0; i4 < 8; ++i4) {
                  if(aboolean[(l1 * 16 + i3) * 8 + i4]) {
                     p_180709_1_.func_180501_a(p_180709_3_.func_177982_a(l1, i4, i3), i4 >= 4?Blocks.field_150350_a.func_176223_P():this.field_150556_a.func_176223_P(), 2);
                  }
               }
            }
         }

         for(int i2 = 0; i2 < 16; ++i2) {
            for(int j3 = 0; j3 < 16; ++j3) {
               for(int j4 = 4; j4 < 8; ++j4) {
                  if(aboolean[(i2 * 16 + j3) * 8 + j4]) {
                     BlockPos blockpos = p_180709_3_.func_177982_a(i2, j4 - 1, j3);
                     if(p_180709_1_.func_180495_p(blockpos).func_177230_c() == Blocks.field_150346_d && p_180709_1_.func_175642_b(EnumSkyBlock.SKY, p_180709_3_.func_177982_a(i2, j4, j3)) > 0) {
                        BiomeGenBase biomegenbase = p_180709_1_.func_180494_b(blockpos);
                        if(biomegenbase.field_76752_A.func_177230_c() == Blocks.field_150391_bh) {
                           p_180709_1_.func_180501_a(blockpos, Blocks.field_150391_bh.func_176223_P(), 2);
                        } else {
                           p_180709_1_.func_180501_a(blockpos, Blocks.field_150349_c.func_176223_P(), 2);
                        }
                     }
                  }
               }
            }
         }

         if(this.field_150556_a.func_149688_o() == Material.field_151587_i) {
            for(int j2 = 0; j2 < 16; ++j2) {
               for(int k3 = 0; k3 < 16; ++k3) {
                  for(int k4 = 0; k4 < 8; ++k4) {
                     boolean flag1 = !aboolean[(j2 * 16 + k3) * 8 + k4] && (j2 < 15 && aboolean[((j2 + 1) * 16 + k3) * 8 + k4] || j2 > 0 && aboolean[((j2 - 1) * 16 + k3) * 8 + k4] || k3 < 15 && aboolean[(j2 * 16 + k3 + 1) * 8 + k4] || k3 > 0 && aboolean[(j2 * 16 + (k3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(j2 * 16 + k3) * 8 + k4 + 1] || k4 > 0 && aboolean[(j2 * 16 + k3) * 8 + (k4 - 1)]);
                     if(flag1 && (k4 < 4 || p_180709_2_.nextInt(2) != 0) && p_180709_1_.func_180495_p(p_180709_3_.func_177982_a(j2, k4, k3)).func_177230_c().func_149688_o().func_76220_a()) {
                        p_180709_1_.func_180501_a(p_180709_3_.func_177982_a(j2, k4, k3), Blocks.field_150348_b.func_176223_P(), 2);
                     }
                  }
               }
            }
         }

         if(this.field_150556_a.func_149688_o() == Material.field_151586_h) {
            for(int k2 = 0; k2 < 16; ++k2) {
               for(int l3 = 0; l3 < 16; ++l3) {
                  int l4 = 4;
                  if(p_180709_1_.func_175675_v(p_180709_3_.func_177982_a(k2, l4, l3))) {
                     p_180709_1_.func_180501_a(p_180709_3_.func_177982_a(k2, l4, l3), Blocks.field_150432_aD.func_176223_P(), 2);
                  }
               }
            }
         }

         return true;
      }
   }
}
