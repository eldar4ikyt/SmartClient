package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderPearl extends EntityThrowable {
   private EntityLivingBase field_181555_c;

   public EntityEnderPearl(World p_i46455_1_) {
      super(p_i46455_1_);
   }

   public EntityEnderPearl(World p_i1783_1_, EntityLivingBase p_i1783_2_) {
      super(p_i1783_1_, p_i1783_2_);
      this.field_181555_c = p_i1783_2_;
   }

   public EntityEnderPearl(World p_i1784_1_, double p_i1784_2_, double p_i1784_4_, double p_i1784_6_) {
      super(p_i1784_1_, p_i1784_2_, p_i1784_4_, p_i1784_6_);
   }

   protected void func_70184_a(MovingObjectPosition p_70184_1_) {
      EntityLivingBase entitylivingbase = this.func_85052_h();
      if(p_70184_1_.field_72308_g != null) {
         if(p_70184_1_.field_72308_g == this.field_181555_c) {
            return;
         }

         p_70184_1_.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, entitylivingbase), 0.0F);
      }

      for(int i = 0; i < 32; ++i) {
         this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t, this.field_70163_u + this.field_70146_Z.nextDouble() * 2.0D, this.field_70161_v, this.field_70146_Z.nextGaussian(), 0.0D, this.field_70146_Z.nextGaussian(), new int[0]);
      }

      if(!this.field_70170_p.field_72995_K) {
         if(entitylivingbase instanceof EntityPlayerMP) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entitylivingbase;
            if(entityplayermp.field_71135_a.func_147362_b().func_150724_d() && entityplayermp.field_70170_p == this.field_70170_p && !entityplayermp.func_70608_bn()) {
               if(this.field_70146_Z.nextFloat() < 0.05F && this.field_70170_p.func_82736_K().func_82766_b("doMobSpawning")) {
                  EntityEndermite entityendermite = new EntityEndermite(this.field_70170_p);
                  entityendermite.func_175496_a(true);
                  entityendermite.func_70012_b(entitylivingbase.field_70165_t, entitylivingbase.field_70163_u, entitylivingbase.field_70161_v, entitylivingbase.field_70177_z, entitylivingbase.field_70125_A);
                  this.field_70170_p.func_72838_d(entityendermite);
               }

               if(entitylivingbase.func_70115_ae()) {
                  entitylivingbase.func_70078_a((Entity)null);
               }

               entitylivingbase.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
               entitylivingbase.field_70143_R = 0.0F;
               entitylivingbase.func_70097_a(DamageSource.field_76379_h, 5.0F);
            }
         } else if(entitylivingbase != null) {
            entitylivingbase.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entitylivingbase.field_70143_R = 0.0F;
         }

         this.func_70106_y();
      }

   }

   public void func_70071_h_() {
      EntityLivingBase entitylivingbase = this.func_85052_h();
      if(entitylivingbase != null && entitylivingbase instanceof EntityPlayer && !entitylivingbase.func_70089_S()) {
         this.func_70106_y();
      } else {
         super.func_70071_h_();
      }

   }
}
